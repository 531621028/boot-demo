package com.hk.demo.exception;

/**
 * Created by hukangkang 2018/8/15
 */
public class ClientException extends RuntimeException {

    private int errorCode = 404;

    public ClientException() {
        super();
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
