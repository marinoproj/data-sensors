package br.com.marinoprojetos.datasensors.controller;

import br.com.marinoprojetos.datasensors.domain.Device;
import br.com.marinoprojetos.datasensors.dto.request.RequestPostDeviceDTO;
import br.com.marinoprojetos.datasensors.dto.request.RequestPutDeviceDTO;
import br.com.marinoprojetos.datasensors.service.utils.DeviceUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/device")
public class DeviceController {

    @Autowired
    private DeviceUtilsService deviceUtilsService;

    @PostMapping
    public ResponseEntity<Device> add(@RequestBody RequestPostDeviceDTO request){
        Device device = deviceUtilsService.addDevice(request);
        return ResponseEntity.ok(device);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deviceUtilsService.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<Device>> findDevices(@RequestParam(required = false) Long id){
        return ResponseEntity.ok(deviceUtilsService.findDevices(id));
    }

    @PutMapping
    public ResponseEntity<Device> update(@RequestBody RequestPutDeviceDTO request){
        return ResponseEntity.ok(deviceUtilsService.update(request));
    }

    @PutMapping("/{deviceId}/reading-time/{readingTime}")
    public ResponseEntity<Device> updateReadingTime(@PathVariable long deviceId, @PathVariable long readingTime){
        return ResponseEntity.ok(deviceUtilsService.updateReadingTime(deviceId, readingTime));
    }

}