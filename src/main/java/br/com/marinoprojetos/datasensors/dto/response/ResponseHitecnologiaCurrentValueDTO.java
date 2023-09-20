package br.com.marinoprojetos.datasensors.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseHitecnologiaCurrentValueDTO {

    private long id;
    private String value;

    @JsonProperty("date_time")
    private String dateTime;

}