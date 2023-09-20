package br.com.marinoprojetos.datasensors.repository;

import br.com.marinoprojetos.datasensors.domain.Connector;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConnectorRepository extends CrudRepository<Connector, Long> {

    @Override
    List<Connector> findAll();

    Optional<Connector> findByExternalId(Long externalId);

}
