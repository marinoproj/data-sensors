package br.com.marinoprojetos.datasensors.client;

import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "hitecnologiaDataClient", url = "${hitecnologia.api.base_url}")
public interface HitecnologiaDataClient {

    @GetMapping("/data")
    List<ResponseHitecnologiaDataDTO> geDataByDeviceId(@RequestHeader("Authorization") String token,
                                                       @RequestParam("device_id") Long deviceId);

    @GetMapping("/data")
    List<ResponseHitecnologiaDataDTO> geDataByIds(@RequestHeader("Authorization") String token,
                                                  @RequestParam("ids") List<Long> ids);

}