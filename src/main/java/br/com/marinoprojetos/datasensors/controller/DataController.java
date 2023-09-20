package br.com.marinoprojetos.datasensors.controller;

import br.com.marinoprojetos.datasensors.domain.DataDevice;
import br.com.marinoprojetos.datasensors.dto.request.RequestPostDataLoadDTO;
import br.com.marinoprojetos.datasensors.dto.request.RequestPutDataDTO;
import br.com.marinoprojetos.datasensors.service.utils.DataUtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/data")
public class DataController {

    @Autowired
    private DataUtilsService dataUtilsService;

    @PostMapping("/load")
    public ResponseEntity<List<DataDevice>> loadData(@RequestBody RequestPostDataLoadDTO request){
        List<DataDevice> dataDeviceList = dataUtilsService.loadData(request.getDeviceId());
        return ResponseEntity.ok(dataDeviceList);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        dataUtilsService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<DataDevice> update(@RequestBody RequestPutDataDTO request){
        return ResponseEntity.ok(dataUtilsService.update(request));
    }

}