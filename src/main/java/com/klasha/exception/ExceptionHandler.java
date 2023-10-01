package com.klasha.exception;

import com.klasha.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String, Map<String, List<String>>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.failure(getErrorsMap(errors),400));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("Errors", errors);
        return errorResponse;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<Object,Object>> handleBadRequest(ApplicationException ex) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.failure(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ClientException.class)
    public ResponseEntity<ApiResponse<Object,Object>> handleCustomException(ClientException ex) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.failure(ex.getMessage(),HttpStatus.BAD_REQUEST.value()));
    }

}