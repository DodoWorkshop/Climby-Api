package com.dodoworkshop.climbyapi.exception.handler;

import com.dodoworkshop.climbyapi.api.response.ErrorResponse;
import com.dodoworkshop.climbyapi.exception.type.api.ApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ApiException.class})
    protected ResponseEntity<Object> handleApiException(ApiException e, WebRequest request) {
        final ErrorResponse response = ErrorResponse.builder()
                .errorType(e.getClass())
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, response, new HttpHeaders(), e.getStatus(), request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGenericException(Exception e, WebRequest request) {
        final ErrorResponse response = ErrorResponse.builder()
                .errorType(e.getClass())
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
