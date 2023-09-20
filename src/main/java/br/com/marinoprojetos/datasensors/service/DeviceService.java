package br.com.marinoprojetos.datasensors.service;

import br.com.marinoprojetos.datasensors.domain.Device;
import br.com.marinoprojetos.datasensors.dto.request.RequestPutDeviceDTO;
import br.com.marinoprojetos.datasensors.exception.GeneralInvalidException;
import br.com.marinoprojetos.datasensors.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Optional<Device> getById(Long id){
        return deviceRepository.findById(id);
    }

    public List<Device> getDevices(){
        return deviceRepository.findAll();
    }

    public List<Device> getDevicesByConnectorId(Long connectorId){
        return deviceRepository.findByConnectorId(connectorId);
    }

    public Device save(Device device){
        return deviceRepository.save(device);
    }

    public Optional<Device> getDeviceByExternalId(Long externalId){
        return deviceRepository.findByExternalId(externalId);
    }

    public void delete(Long id){
        Optional<Device> deviceOptional = getById(id);
        if (!deviceOptional.isPresent()){
            return;
        }
        Device device = deviceOptional.get();
        if (device.getDhExclude() != null){
            return;
        }
        device.setDhExclude(LocalDateTime.now());
        save(device);
    }

}