package com.codestoreapi.exceptions;

public class ForbiddenException extends RuntimeException {

    private static final String DESCRIPTION = "Forbbiden (403)";

    public  ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
