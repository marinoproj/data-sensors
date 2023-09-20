package br.com.marinoprojetos.datasensors.service;

import br.com.marinoprojetos.datasensors.domain.DataDevice;
import br.com.marinoprojetos.datasensors.repository.DataDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DataDeviceService {

    @Autowired
    private DataDeviceRepository dataDeviceRepository;

    public List<DataDevice> getAllDataDevice(){
        return dataDeviceRepository.findAll();
    }

    public List<DataDevice> getAllDataDeviceByDeviceId(Long deviceId){
        return dataDeviceRepository.findByDeviceId(deviceId);
    }

    public DataDevice save(DataDevice dataDevice){
        return dataDeviceRepository.save(dataDevice);
    }

    public Optional<DataDevice> getDataDeviceByExternalId(Long externalId){
        return dataDeviceRepository.findByExternalId(externalId);
    }

    public Optional<DataDevice> getById(Long id){
        return dataDeviceRepository.findById(id);
    }

    public void delete(Long id){
        Optional<DataDevice> dataDeviceOptional = getById(id);
        if (!dataDeviceOptional.isPresent()){
            return;
        }
        DataDevice dataDevice = dataDeviceOptional.get();
        if (dataDevice.getDhExclude() != null){
            return;
        }
        dataDevice.setDhExclude(LocalDateTime.now());
        save(dataDevice);
    }

}