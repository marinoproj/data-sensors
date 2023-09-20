package br.com.marinoprojetos.datasensors.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseHitecnologiaMemberContractDTO {

    private Long id;

    @JsonProperty("owner_id")
    private String ownerId;

    private String name;
    private String description;

}
