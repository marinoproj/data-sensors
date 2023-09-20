package br.com.marinoprojetos.datasensors.service.hitecnologia;

import br.com.marinoprojetos.datasensors.client.HitecnologiaDeviceClient;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDeviceDTO;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class HitecnologiaDeviceService {

    @Autowired
    private HitecnologiaTokenService hitecnologiaTokenService;

    @Autowired
    private HitecnologiaDeviceClient hitecnologiaDeviceClient;

    public Optional<ResponseHitecnologiaDeviceDTO> getDeviceById(Long id){

        ResponseHitecnologiaTokenDTO token = hitecnologiaTokenService.getToken();

        List<ResponseHitecnologiaDeviceDTO> devices = hitecnologiaDeviceClient.getDeviceByIds(token.getBearerToken(), Collections.singletonList(id));

        if (devices.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(devices.get(0));

    }

}