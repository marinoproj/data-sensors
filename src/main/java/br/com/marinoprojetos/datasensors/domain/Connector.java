package br.com.marinoprojetos.datasensors.domain;

import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaConnectorDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_conector", schema = "dados")
public class Connector {

    @Id
    @Column(name = "id_conector")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_contrato")
    private long contractId;

    @Column(name = "id_externo")
    private long externalId;

    @Column(name = "nome_externo")
    private String externalName;

    @Column(name = "label")
    private String label;

    @Column(name = "descricao")
    private String description;

    @Column(name = "ativo")
    private boolean enabled;

    @Column(name = "conectado")
    private boolean isConnected;

    @Column(name = "dh_exclusao")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dhExclude;

    public static Connector toDTO(ResponseHitecnologiaConnectorDTO dto){
        Connector connector = new Connector();
        connector.setContractId(dto.getContractId());
        connector.setExternalId(dto.getId());
        connector.setExternalName(dto.getName());
        connector.setEnabled(dto.isEnabled());
        connector.setConnected(dto.isConnected());
        return connector;
    }

}