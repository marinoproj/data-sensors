package br.com.marinoprojetos.datasensors.dto.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RequestHitecnologiaTokenDTO {

    private String email;
    private String password;

}
