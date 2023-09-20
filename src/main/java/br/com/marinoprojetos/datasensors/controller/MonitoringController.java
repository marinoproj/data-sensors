package br.com.marinoprojetos.datasensors.controller;

import br.com.marinoprojetos.datasensors.dto.response.ResponseMonitoringDeviceDTO;
import br.com.marinoprojetos.datasensors.service.utils.MonitoringUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringUtilsService monitoringUtilsService;

    @PostMapping("/start/all")
    public ResponseEntity<List<ResponseMonitoringDeviceDTO>> startAll(){
        monitoringUtilsService.startAllMonitoringDevice();
        return ResponseEntity.ok(monitoringUtilsService.statusMonitoringDevice());
    }

    @PostMapping("/stop/all")
    public ResponseEntity<List<ResponseMonitoringDeviceDTO>> stopAll(){
        monitoringUtilsService.stopAllMonitoringDevice();
        return ResponseEntity.ok(monitoringUtilsService.statusMonitoringDevice());
    }

    @PostMapping("/start/{deviceId}")
    public void startDevice(@PathVariable Long deviceId){
        monitoringUtilsService.startMonitoringDevice(deviceId);
    }

    @PostMapping("/stop/{deviceId}")
    public void stopDevice(@PathVariable Long deviceId){
        monitoringUtilsService.stopMonitoringDevice(deviceId);
    }

    @GetMapping("/status")
    public ResponseEntity<List<ResponseMonitoringDeviceDTO>> status(){
        return ResponseEntity.ok(monitoringUtilsService.statusMonitoringDevice());
    }

}