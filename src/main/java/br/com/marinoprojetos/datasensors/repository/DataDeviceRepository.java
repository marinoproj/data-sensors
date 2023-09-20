package br.com.marinoprojetos.datasensors.repository;

import br.com.marinoprojetos.datasensors.domain.DataDevice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DataDeviceRepository extends CrudRepository<DataDevice, Long> {

    @Override
    List<DataDevice> findAll();

    List<DataDevice> findByDeviceId(Long deviceId);

    Optional<DataDevice> findByExternalId(Long externalId);

}
