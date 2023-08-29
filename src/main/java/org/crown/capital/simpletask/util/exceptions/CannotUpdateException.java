package org.crown.capital.simpletask.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotUpdateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CannotUpdateException() {
        super();
    }

    public CannotUpdateException(String message) {
        super(message);
    }

    public CannotUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

}
