package org.crown.capital.simpletask.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotCreateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CannotCreateException() {
        super();
    }

    public CannotCreateException(String message) {
        super(message);
    }

    public CannotCreateException(String message, Throwable cause) {
        super(message, cause);
    }

}