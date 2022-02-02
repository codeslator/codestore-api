package com.codestoreapi.exceptions;

import com.codestoreapi.exceptions.responses.ErrorResponse;
import com.codestoreapi.exceptions.responses.ErrorResponse.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ NotFoundException.class })
    @ResponseBody
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    // Peticiones mal formuladas
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            BadRequestException.class,
            ServletException.class,
            org.springframework.dao.DuplicateKeyException.class,
            org.springframework.web.HttpRequestMethodNotSupportedException.class,
            org.springframework.web.bind.MethodArgumentNotValidException.class,
            org.springframework.web.bind.MissingRequestHeaderException.class,
            org.springframework.web.bind.MissingServletRequestParameterException.class,
            org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class,
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    // Excepciones desconocidas
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ErrorMessage internalServerErrorException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    // Excepciones con conflictos
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({ ConflictException.class })
    @ResponseBody
    public ErrorMessage conflictException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    // Excepciones con status prohibido
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({ ForbiddenException.class })
    @ResponseBody
    public ErrorMessage forbbidenException(HttpServletRequest request, Exception exception) {
        return new ErrorMessage(exception, request.getRequestURI());
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse fieldErrors(HttpServletRequest request, BindException ex) {

        // Errores
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        // Detalles
        List<ErrorDetails> errorDetails = new ArrayList<>();

        for (FieldError error : errors) {
            ErrorDetails detail = new ErrorDetails();
            detail.setFieldName(error.getField());
            detail.setMessage(error.getDefaultMessage());
            errorDetails.add(detail);
        }

        ErrorResponse response = new ErrorResponse(ex, request.getRequestURI(), errorDetails);

        return response;
    }
}
