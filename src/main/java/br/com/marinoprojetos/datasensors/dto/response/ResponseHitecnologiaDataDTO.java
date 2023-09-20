package br.com.marinoprojetos.datasensors.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseHitecnologiaDataDTO {

    private long id;

    @JsonProperty("contract_id")
    private long contractId;

    private String name;

    private boolean enabled;

    private ResponseHitecnologiaDeviceDTO device;

    @JsonProperty("memory_type")
    private ResponseHitecnologiaMemoryTypeDTO memoryType;

    @JsonProperty("memory_size")
    private Long memorySize;

    @JsonProperty("value_format_type")
    private ResponseHitecnologiaValueFormatTypeDTO valueFormatType;

    @JsonProperty("memory_address")
    private Long memoryAddress;

    @JsonProperty("current_value")
    private ResponseHitecnologiaCurrentValueDTO currentValue;

    @JsonProperty("read_only")
    private boolean readOnly;

    @JsonProperty("read_write_mode")
    private long readWriteMode;

}