package br.com.marinoprojetos.datasensors.service;

import br.com.marinoprojetos.datasensors.domain.ValueData;
import br.com.marinoprojetos.datasensors.repository.ValueDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValueDataService {

    @Autowired
    private ValueDataRepository valueDataRepository;

    public List<ValueData> getAllValueData(){
        return valueDataRepository.findAll();
    }

    public List<ValueData> getValueDataByDeviceId(Long dataDeviceId){
        return valueDataRepository.findByDataDeviceId(dataDeviceId);
    }

    public ValueData save(ValueData valueData){
        return valueDataRepository.save(valueData);
    }

    public Optional<ValueData> getByExternalId(long externalId){
        return valueDataRepository.findByExternalId(externalId);
    }

}