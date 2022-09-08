package com.heroes.clientes.config.exception;

import com.heroes.clientes.config.exception.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;
@Slf4j
@Component
@RestControllerAdvice
public class ClientExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String S_FOR_S = "%s for %s";

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request){
        logException(ex, status);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request){

        String errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        String userMessage = String.format(S_FOR_S, status.getReasonPhrase(), ex.getBindingResult().getObjectName());

        ErrorModel errorModel = ErrorModel.builder()
                .statusCode(status.value())
                .userMessage(userMessage)
                .detailMessage(errors).build();

        return handleExceptionInternal(ex, errorModel, headers, status, request);

    }

    private void logException(Exception ex, HttpStatus status){
        log.error(String.format("Error (%d) %s: %s",
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()));
    }
}