package br.com.marinoprojetos.datasensors.monitoring;

import br.com.marinoprojetos.datasensors.domain.DataDevice;
import br.com.marinoprojetos.datasensors.domain.Device;
import br.com.marinoprojetos.datasensors.domain.ValueData;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaCurrentValueDTO;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDataDTO;
import br.com.marinoprojetos.datasensors.service.ConnectorService;
import br.com.marinoprojetos.datasensors.service.DataDeviceService;
import br.com.marinoprojetos.datasensors.service.DeviceService;
import br.com.marinoprojetos.datasensors.service.ValueDataService;
import br.com.marinoprojetos.datasensors.service.hitecnologia.HitecnologiaDataService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class DeviceMonitoringJob extends Thread {

    private final Logger LOG = LoggerFactory.getLogger(DeviceMonitoringJob.class);

    private ConnectorService connectorService;
    private DataDeviceService dataDeviceService;
    private DeviceService deviceService;
    private ValueDataService valueDataService;
    private HitecnologiaDataService hitecnologiaDataService;

    private Device device;

    private boolean run;
    private long readingTime;

    public DeviceMonitoringJob(Device device, BeanFactory beanFactory) {

        super();

        this.device = device;

        connectorService = beanFactory.getBean(ConnectorService.class);
        dataDeviceService = beanFactory.getBean(DataDeviceService.class);
        deviceService = beanFactory.getBean(DeviceService.class);
        valueDataService = beanFactory.getBean(ValueDataService.class);
        hitecnologiaDataService = beanFactory.getBean(HitecnologiaDataService.class);

        run = false;
        readingTime = device.getReadingTime() == null ? 30 : device.getReadingTime(); // default 30 segundos

    }

    @Override
    public void run() {

        run = true;

        LOG.info("START monitoring device: {}", device.getId());

        while (run) {

            try {

                LOG.info("INIT monitoring device: {}", device.getId());

                List<DataDevice> dataDeviceList = dataDeviceService.getAllDataDeviceByDeviceId(device.getId());

                LOG.info("monitoring device: {} dataDeviceList.size: {}", device.getId(), dataDeviceList.size());

                List<ResponseHitecnologiaDataDTO> dataExternalList = hitecnologiaDataService.getDataByDeviceId(device.getExternalId());

                LOG.info("monitoring device: {} dataExternalList.size: {}", device.getId(), dataExternalList.size());

                for (DataDevice dataDevice: dataDeviceList){

                    if (!run){
                        break;
                    }

                    Optional<ResponseHitecnologiaDataDTO> dataExternalOptional = dataExternalList
                            .stream()
                            .filter(obj -> obj.getId() == dataDevice.getExternalId())
                            .findFirst();

                    if (dataExternalOptional.isPresent()) {

                        LOG.info("monitoring device: {} dataDevice: {} dataDeviceName: {} found in dataExternalList", device.getId(), dataDevice.getId(), dataDevice.getExternalName());

                        ResponseHitecnologiaDataDTO dataExternal = dataExternalOptional.get();

                        // update
                        dataDevice.setExternalName(dataExternal.getName());
                        dataDevice.setEnabled(dataExternal.isEnabled());
                        dataDevice.setMemorySize(dataExternal.getMemorySize());
                        dataDevice.setMemoryType(dataExternal.getMemoryType().getName());
                        dataDevice.setMemoryAddress(dataExternal.getMemoryAddress());
                        dataDevice.setValueFormatType(dataExternal.getValueFormatType().getName());
                        dataDevice.setReadOnly(dataExternal.isReadOnly());
                        dataDevice.setReadWriteMode(dataExternal.getReadWriteMode());
                        dataDeviceService.save(dataDevice);

                        LOG.info("monitoring device: {} updated dataDevice: {} dataDeviceName: {}", device.getId(), dataDevice.getId(), dataDevice.getExternalName());

                        if (dataExternal.getCurrentValue() != null) {

                            ResponseHitecnologiaCurrentValueDTO currentValueExternal = dataExternal.getCurrentValue();
                            LocalDateTime dhReading = LocalDateTime.parse(currentValueExternal.getDateTime(), DateTimeFormatter.ISO_DATE_TIME);

                            Optional<ValueData> valueDataCurrent = valueDataService.findFirstByDataDeviceIdOrderByDhReadingDesc(dataDevice.getId());

                            boolean update = false;

                            if (valueDataCurrent.isPresent()){
                                if (valueDataCurrent.get().getDhReading().isBefore(dhReading)){
                                    update = true;
                                }
                            } else {
                                update = true;
                            }

                            if (update) {

                                ValueData valueData = new ValueData();

                                valueData.setValue(currentValueExternal.getValue());
                                valueData.setDhReading(dhReading);
                                valueData.setDhCreate(LocalDateTime.now());
                                valueData.setDataDevice(dataDevice);
                                valueData = valueDataService.save(valueData);

                                LOG.info("monitoring device: {} insert new valuedata dataDevice: {} dataDeviceName: {}", device.getId(), dataDevice.getId(), dataDevice.getExternalName());

                                dataDevice.setCurrentValueData(valueData);
                                dataDeviceService.save(dataDevice);

                                LOG.info("monitoring device: {} updated current value dataDevice: {} dataDeviceName: {}", device.getId(), dataDevice.getId(), dataDevice.getExternalName());

                            } else {

                                LOG.info("monitoring device: {} current value has not changed dataDevice: {} dataDeviceName: {}", device.getId(), dataDevice.getId(), dataDevice.getExternalName());

                            }

                        }

                    } else {

                        LOG.warn("monitoring device: {} dataDevice: {} dataDeviceName: {} not found in dataExternalList", device.getId(), dataDevice.getId(), dataDevice.getExternalName());

                    }

                }

                LOG.info("END monitoring device: {}. WAITING {} seconds", device.getId(), readingTime);

            } catch (Exception ex){
                LOG.error("ERROR monitoring device: {}. message: {}", device.getId(), readingTime, ex.getMessage());
            }

            try {
                Thread.sleep(readingTime * 1000); // default 30 segundos
            }catch (Exception ignored){}

        }

    }

    public void close(){
        run = false;
        LOG.info("CLOSE monitoring device: {}", device.getId());
    }

}
