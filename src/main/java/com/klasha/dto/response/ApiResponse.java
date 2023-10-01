package com.klasha.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.klasha.constants.AppConstants;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class ApiResponse<D,E> implements Serializable {
    private String message;
    private int status;
    private D data;
    private E errorData;
    private Instant timestamp;

    public static<D,E> ApiResponse<D,E> success(D data){
        return new ApiResponse<>(AppConstants.SUCCESS_MESSAGE,200,data,null,Instant.now());
    }
    public static<D,E> ApiResponse<D,E> failure(E errorData,int status){
        return new ApiResponse<>(AppConstants.FAILURE_MESSAGE,status,null,errorData,Instant.now());
    }
}