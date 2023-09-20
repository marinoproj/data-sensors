package br.com.marinoprojetos.datasensors.repository;

import br.com.marinoprojetos.datasensors.domain.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, Long> {

    @Override
    List<Device> findAll();

    List<Device> findByConnectorId(Long connectorId);

    Optional<Device> findByExternalId(Long externalId);

}
