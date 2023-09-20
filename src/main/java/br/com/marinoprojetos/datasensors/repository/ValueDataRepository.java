package br.com.marinoprojetos.datasensors.repository;

import br.com.marinoprojetos.datasensors.domain.ValueData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ValueDataRepository extends CrudRepository<ValueData, Long> {

    @Override
    List<ValueData> findAll();

    List<ValueData> findByDataDeviceId(Long dataDeviceId);

    Optional<ValueData> findByExternalId(Long externalId);

}