package br.com.marinoprojetos.datasensors.service.utils;

import br.com.marinoprojetos.datasensors.domain.DataDevice;
import br.com.marinoprojetos.datasensors.domain.Device;
import br.com.marinoprojetos.datasensors.dto.request.RequestPutDataDTO;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDataDTO;
import br.com.marinoprojetos.datasensors.exception.GeneralInvalidException;
import br.com.marinoprojetos.datasensors.service.DataDeviceService;
import br.com.marinoprojetos.datasensors.service.DeviceService;
import br.com.marinoprojetos.datasensors.service.hitecnologia.HitecnologiaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataUtilsService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DataDeviceService dataDeviceService;

    @Autowired
    private HitecnologiaDataService hitecnologiaDataService;

    public List<DataDevice> loadData(Long deviceId){

        Optional<Device> deviceOptional = deviceService.getById(deviceId);

        if (!deviceOptional.isPresent()){
            throw new GeneralInvalidException("error.load.data", "Dispositivo não encontrado");
        }

        Device device = deviceOptional.get();

        List<ResponseHitecnologiaDataDTO> dataExternalList = hitecnologiaDataService.getDataByDeviceId(device.getExternalId());

        List<DataDevice> dataDeviceList = dataDeviceService.getAllDataDeviceByDeviceId(deviceId);

        dataExternalList.forEach(dataExternal -> {

            Optional<DataDevice> dataDeviceOptional = dataDeviceList.stream().filter(dt -> dt.getExternalId() == dataExternal.getId()).findFirst();

            if (dataDeviceOptional.isPresent()){
                // update
                DataDevice dataDeviceUpdate = dataDeviceOptional.get();
                dataDeviceUpdate.setExternalName(dataExternal.getName());
                dataDeviceUpdate.setEnabled(dataExternal.isEnabled());
                dataDeviceUpdate.setMemorySize(dataExternal.getMemorySize());
                dataDeviceUpdate.setMemoryType(dataExternal.getMemoryType().getName());
                dataDeviceUpdate.setMemoryAddress(dataExternal.getMemoryAddress());
                dataDeviceUpdate.setValueFormatType(dataExternal.getValueFormatType().getName());
                dataDeviceUpdate.setReadOnly(dataExternal.isReadOnly());
                dataDeviceUpdate.setReadWriteMode(dataExternal.getReadWriteMode());
                dataDeviceService.save(dataDeviceUpdate);

            } else {
                // novo
                DataDevice dataDeviceNew = DataDevice.toDTO(dataExternal);
                dataDeviceNew.setDevice(device);
                dataDeviceService.save(dataDeviceNew);

            }

        });

        dataDeviceList.forEach(dataDevice -> {

            Optional<ResponseHitecnologiaDataDTO> dataExternalOptional = dataExternalList.stream().filter(de -> de.getId() == dataDevice.getExternalId()).findFirst();

            if (!dataExternalOptional.isPresent()){
                // deletar
                dataDeviceService.delete(dataDevice.getId());
            }

        });

        return dataDeviceService.getAllDataDeviceByDeviceId(deviceId);

    }

    public void deleteById(Long dataId){

        Optional<DataDevice> dataDeviceOptional = dataDeviceService.getById(dataId);

        if (!dataDeviceOptional.isPresent()){
            // data device não existe
            throw new GeneralInvalidException("error.delete.data", "Data não encontrado");
        }

        dataDeviceService.delete(dataId);

    }

    public DataDevice update(RequestPutDataDTO request){

        Optional<DataDevice> dataDeviceOptional = dataDeviceService.getById(request.getId());

        if (!dataDeviceOptional.isPresent()){
            throw new GeneralInvalidException("error.update.data", "Data não encontrado");
        }

        DataDevice dataDevice = dataDeviceOptional.get();
        dataDevice.setLabel(request.getLabel());
        dataDevice.setDescription(request.getDescription());
        dataDevice.setDisplay(request.getDisplay());

        return dataDeviceService.save(dataDevice);

    }

    public List<DataDevice> findByDeviceId(long deviceId){
        List<DataDevice> response = dataDeviceService.getAllDataDeviceByDeviceId(deviceId);
        response.forEach(obj -> {
            if (obj.getCurrentValueData() != null){
                obj.getCurrentValueData().setDataDevice(null);
            }
        });
        return response;
    }

    public Optional<DataDevice> findById(long id){
        Optional<DataDevice> response = dataDeviceService.getById(id);
        return response.map(obj -> {
            if (obj.getCurrentValueData() != null){
                obj.getCurrentValueData().setDataDevice(null);
            }
            return obj;
        });
    }

}