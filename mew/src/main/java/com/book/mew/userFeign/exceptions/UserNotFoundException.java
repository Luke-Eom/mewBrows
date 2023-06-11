package com.book.mew.userFeign.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String errorCode;
    private Map<String, Object> body;

    public UserNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public UserNotFoundException(String errorCode, String message, Map<String, Object> body) {
        super(message);
        this.errorCode = errorCode;
        this.body = body;
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getBody() { return body; }
}
