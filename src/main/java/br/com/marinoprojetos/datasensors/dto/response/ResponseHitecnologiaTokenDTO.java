package br.com.marinoprojetos.datasensors.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseHitecnologiaTokenDTO {

    private String token;

    @JsonProperty("is_member_contracts")
    private List<ResponseHitecnologiaMemberContractDTO> isMemberContracts;

    public String getBearerToken(){
        return "Bearer " + token;
    }

}