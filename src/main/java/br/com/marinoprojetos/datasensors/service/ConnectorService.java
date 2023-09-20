package br.com.marinoprojetos.datasensors.service;

import br.com.marinoprojetos.datasensors.domain.Connector;
import br.com.marinoprojetos.datasensors.repository.ConnectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectorService {

    @Autowired
    private ConnectorRepository connectorRepository;

    public List<Connector> getConnectors(){
        return connectorRepository.findAll();
    }

    public Connector save(Connector connector){
        return connectorRepository.save(connector);
    }

    public Optional<Connector> getConnectorByExternalId(Long externalId){
        return connectorRepository.findByExternalId(externalId);
    }

}