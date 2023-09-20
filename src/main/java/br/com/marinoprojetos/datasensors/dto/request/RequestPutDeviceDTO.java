package br.com.marinoprojetos.datasensors.dto.request;

import lombok.Data;

@Data
public class RequestPutDeviceDTO {

    private Long id;
    private String label;
    private String description;
    private String latitudeLocation;
    private String longitudeLocation;
    private Long readingTime;
    private Boolean display;
    private Boolean startAutomatically;

}