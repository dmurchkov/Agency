package com.dmurchkov.service.agency.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Ad")
public class NoSuchAdException extends RuntimeException {

    public NoSuchAdException(String message) {
        super(message);
    }
}