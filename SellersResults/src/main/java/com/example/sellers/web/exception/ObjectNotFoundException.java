package com.example.sellers.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

    private final String value;

    public ObjectNotFoundException(String message, String value) {
        super(message);
        this.value = value;
    }

    public String getId() {
        return value;
    }
}
