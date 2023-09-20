package br.com.marinoprojetos.datasensors.dto.request;

import lombok.Data;

@Data
public class RequestPutDataDTO {

    private Long id;
    private String label;
    private String description;
    private Boolean display;

}