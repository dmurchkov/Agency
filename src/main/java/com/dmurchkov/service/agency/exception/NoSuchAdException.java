package com.dmurchkov.service.agency.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Ad")
public class NoSuchAdException extends RuntimeException {

    public NoSuchAdException() {
    }

    public NoSuchAdException(String message) {
        super(message);
    }
}
