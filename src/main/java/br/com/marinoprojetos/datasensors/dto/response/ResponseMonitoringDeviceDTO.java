package br.com.marinoprojetos.datasensors.dto.response;

import br.com.marinoprojetos.datasensors.enums.StatusMonitoringDeviceEnum;
import lombok.Data;

@Data
public class ResponseMonitoringDeviceDTO {

    private long deviceId;
    private long deviceExternalId;
    private String deviceExternalName;
    private StatusMonitoringDeviceEnum statusMonitoring;

}