package br.com.marinoprojetos.datasensors.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseHitecnologiaDeviceDTO {

    private long id;

    @JsonProperty("contract_id")
    private long contractId;

    private String name;
    private boolean enabled;

    @JsonProperty("is_connected")
    private boolean isConnected;

    private ResponseHitecnologiaConnectorDTO connector;

}