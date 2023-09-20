package br.com.marinoprojetos.datasensors.service.utils;

import br.com.marinoprojetos.datasensors.domain.Device;
import br.com.marinoprojetos.datasensors.dto.response.ResponseMonitoringDeviceDTO;
import br.com.marinoprojetos.datasensors.enums.StatusMonitoringDeviceEnum;
import br.com.marinoprojetos.datasensors.exception.GeneralInvalidException;
import br.com.marinoprojetos.datasensors.monitoring.DeviceMonitoringService;
import br.com.marinoprojetos.datasensors.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MonitoringUtilsService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceMonitoringService deviceMonitoringService;

    public void startAllMonitoringDevice() {
        List<Device> deviceList = deviceService.getDevices();

        deviceList.forEach(device -> {
            deviceMonitoringService.startMonitoringDevice(device);
        });
    }

    public void stopAllMonitoringDevice(){
        deviceMonitoringService.stopAllMonitoringDevice();
    }

    public void startMonitoringDevice(long deviceId) {
        Optional<Device> deviceOptional = deviceService.getById(deviceId);

        if (!deviceOptional.isPresent()){
            throw new GeneralInvalidException("error.start.monitoring", "Dispositivo não encontrado");
        }

        deviceMonitoringService.startMonitoringDevice(deviceOptional.get());
    }

    public void stopMonitoringDevice(long deviceId) {
        Optional<Device> deviceOptional = deviceService.getById(deviceId);

        if (!deviceOptional.isPresent()){
            throw new GeneralInvalidException("error.stop.monitoring", "Dispositivo não encontrado");
        }

        deviceMonitoringService.stopMonitoringDevice(deviceOptional.get());
    }

    public List<ResponseMonitoringDeviceDTO> statusMonitoringDevice() {
        List<Device> deviceList = deviceService.getDevices();

        List<ResponseMonitoringDeviceDTO> list = new ArrayList<>();

        deviceList.forEach(device -> {

            StatusMonitoringDeviceEnum status = deviceMonitoringService.statusMonitoringDevice(device);

            ResponseMonitoringDeviceDTO response = new ResponseMonitoringDeviceDTO();
            response.setDeviceId(device.getId());
            response.setDeviceExternalId(device.getExternalId());
            response.setDeviceExternalName(device.getExternalName());
            response.setStatusMonitoring(status);

            list.add(response);

        });

        return list;
    }

}