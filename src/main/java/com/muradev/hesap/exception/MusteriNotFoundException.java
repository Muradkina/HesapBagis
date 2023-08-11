package com.muradev.hesap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MusteriNotFoundException extends RuntimeException {
    public MusteriNotFoundException(String message) {
        super(message);
    }
}
