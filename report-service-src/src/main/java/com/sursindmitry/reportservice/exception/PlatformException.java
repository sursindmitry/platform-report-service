package com.sursindmitry.reportservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Общий Exception платформы.
 */
@Getter
public class PlatformException extends RuntimeException {

    private final HttpStatus httpStatus;

    public PlatformException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
