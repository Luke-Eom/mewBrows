package com.book.mew.chartRecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class StatusException extends RuntimeException {

    private static final long serialVersionUID = 2L;
    private final String errorCode;
    private Map<String, Object> body;

    public StatusException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public StatusException(String errorCode, String message, Map<String, Object> body) {
        super(message);
        this.errorCode = errorCode;
        this.body = body;
    }

    public StatusException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "";
    }

    public String getErrorCode() { return errorCode; }

    public Map<String, Object> getBody() { return body; }

}
