package br.com.marinoprojetos.datasensors.exception;

public class GeneralInvalidException extends RuntimeException{

    public String code;
    public String message;

    public GeneralInvalidException() {
    }

    public GeneralInvalidException(String message) {
        super(message);
        this.message = message;
    }

    public GeneralInvalidException(String code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public GeneralInvalidException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public GeneralInvalidException(Throwable cause) {
        super(cause);
    }

    public GeneralInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

}