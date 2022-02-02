package com.codestoreapi.exceptions;

public class InternalServerErrorException extends RuntimeException {

    private static final String DESCRIPTION = "Internal Server Error Exception (500)";

    public  InternalServerErrorException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
