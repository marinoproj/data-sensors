package br.com.marinoprojetos.datasensors.service.hitecnologia;

import br.com.marinoprojetos.datasensors.client.HitecnologiaTokenClient;
import br.com.marinoprojetos.datasensors.dto.request.RequestHitecnologiaTokenDTO;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HitecnologiaTokenService {

    @Autowired
    private HitecnologiaTokenClient hitecnologiaTokenClient;

    @Autowired
    @Value("${hitecnologia.api.username}")
    private String email;

    @Autowired
    @Value("${hitecnologia.api.password}")
    private String password;

    public ResponseHitecnologiaTokenDTO getToken(){

        RequestHitecnologiaTokenDTO token = new RequestHitecnologiaTokenDTO();
        token.setEmail(email);
        token.setPassword(password);

        return hitecnologiaTokenClient.getToken(token);

    }

}