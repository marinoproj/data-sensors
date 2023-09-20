package br.com.marinoprojetos.datasensors.service.hitecnologia;

import br.com.marinoprojetos.datasensors.client.HitecnologiaDataClient;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDataDTO;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HitecnologiaDataService {

    @Autowired
    private HitecnologiaTokenService hitecnologiaTokenService;

    @Autowired
    private HitecnologiaDataClient hitecnologiaDataClient;

    public List<ResponseHitecnologiaDataDTO> getDataByDeviceId(Long deviceId){

        ResponseHitecnologiaTokenDTO token = hitecnologiaTokenService.getToken();

        return hitecnologiaDataClient.geDataByDeviceId(token.getBearerToken(), deviceId);
    }

    public List<ResponseHitecnologiaDataDTO> getDataByIds(List<Long> ids){

        ResponseHitecnologiaTokenDTO token = hitecnologiaTokenService.getToken();

        return hitecnologiaDataClient.geDataByIds(token.getBearerToken(), ids);
    }

}