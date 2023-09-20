package br.com.marinoprojetos.datasensors.client;

import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDeviceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "hitecnologiaDeviceClient", url = "${hitecnologia.api.base_url}")
public interface HitecnologiaDeviceClient {

    @GetMapping("/devices")
    List<ResponseHitecnologiaDeviceDTO> getDeviceByIds(@RequestHeader("Authorization") String token,
                                                       @RequestParam("ids") List<Long> ids);

}
