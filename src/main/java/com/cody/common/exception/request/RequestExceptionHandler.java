package com.cody.common.exception.request;

import com.cody.common.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class RequestExceptionHandler {

    private static final String DELIMITER = ", ";

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionResponse> requestInvalidExceptionHandler(final BindException ex) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(RequestExceptionCode.PARAMETER_BINDING.getCode())
                .message(RequestExceptionCode.PARAMETER_BINDING.getMessage())
                .description(getBindExceptionDescription(ex))
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ExceptionResponse> requestExceptionHandler(final RequestException ex) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message(ex.getCode().getMessage())
                .build();

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

    private String getBindExceptionDescription(final BindException ex) {
        return ex.getFieldErrors().stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(DELIMITER));
    }

}
