package com.sursindmitry.reportservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exception выбрасывающий {@link HttpStatus#NOT_FOUND}.
 */
@Getter
public class NotFoundException extends PlatformException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
