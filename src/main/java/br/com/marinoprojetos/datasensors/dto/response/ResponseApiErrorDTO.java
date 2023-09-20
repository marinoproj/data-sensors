package br.com.marinoprojetos.datasensors.dto.response;

import lombok.Data;

@Data
public class ResponseApiErrorDTO {

    private Integer statusCode;
    private String messageCode;
    private String message;

}