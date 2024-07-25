package com.cody.common.exception.server;

import com.cody.common.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> runtimeExceptionHandler(final RuntimeException ex) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ServerExceptionCode.INTERNAL_SERVER_ERROR.getCode())
                .message(ServerExceptionCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();

        return ResponseEntity
                .status(ServerExceptionCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(response);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<ExceptionResponse> sqlExceptionHandler(final BadSqlGrammarException ex) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ServerExceptionCode.SQL_ERROR.getCode())
                .message(ServerExceptionCode.SQL_ERROR.getMessage())
                .build();

        return ResponseEntity
                .status(ServerExceptionCode.SQL_ERROR.getStatus())
                .body(response);
    }

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ExceptionResponse> jsonParseExceptionHandler(final ServerException ex) {
        final ExceptionResponse response = ExceptionResponse.builder()
                .code(ex.getCode().getCode())
                .message(ex.getCode().getMessage())
                .build();

        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(response);
    }

}
