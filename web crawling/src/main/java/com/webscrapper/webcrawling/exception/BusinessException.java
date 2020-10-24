package com.webscrapper.webcrawling.exception;

import java.util.Collections;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException {
    private BusinessError errorCode;
    private Map<String, Object> information;
    public BusinessException(String message, BusinessError errorCode, Map<String, Object> information) {
        this(message, null, errorCode, information);
    }
    public BusinessException(String message, BusinessError errorCode) {
        this(message, null, errorCode, Collections.emptyMap());
    }
    public BusinessException(String message, Throwable cause, BusinessError errorCode) {
        this(message, cause, errorCode, Collections.emptyMap());
    }
    public BusinessException(String message, Throwable cause, BusinessError errorCode, Map<String, Object> information) {
        super(message, cause);
        this.errorCode = errorCode;
        this.information = information;
    }
}