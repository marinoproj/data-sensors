package br.com.marinoprojetos.datasensors.client;

import br.com.marinoprojetos.datasensors.dto.request.RequestHitecnologiaTokenDTO;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaTokenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hitecnologiaTokenClient", url = "${hitecnologia.api.base_url}")
public interface HitecnologiaTokenClient {

    @PostMapping("/auth/login/")
    ResponseHitecnologiaTokenDTO getToken(@RequestBody RequestHitecnologiaTokenDTO body);

}
