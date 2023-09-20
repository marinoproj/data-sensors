package br.com.marinoprojetos.datasensors.config;

import br.com.marinoprojetos.datasensors.dto.response.ResponseApiErrorDTO;
import br.com.marinoprojetos.datasensors.exception.GeneralInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerApplication {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GeneralInvalidException.class)
    public ResponseApiErrorDTO generalInvalidException(GeneralInvalidException exception){
        ResponseApiErrorDTO error = new ResponseApiErrorDTO();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setMessageCode(exception.code);
        error.setMessage(exception.message);
        return error;
    }

}
