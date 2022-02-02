package com.codestoreapi.exceptions.responses;

import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    private String exception;

    private String message;

    private List<ErrorDetails> errors;

    private String path;

    public ErrorResponse(Exception exception, String path, List<ErrorDetails> errors) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
        this.errors = errors;
    }


    @Data
    public static class ErrorDetails {
        private String fieldName;
        private String message;
    }

}
