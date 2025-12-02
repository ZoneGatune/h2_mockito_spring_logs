package com.example.springbootswaggerh2.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExternalSystemResponse {

    private boolean success;
    private String message;
    private String systemName;
    private LocalDateTime responseTime;
    private int statusCode;
    private String responseData;

    public static ExternalSystemResponse success(String systemName, String message) {
        ExternalSystemResponse response = new ExternalSystemResponse();
        response.setSuccess(true);
        response.setSystemName(systemName);
        response.setMessage(message);
        response.setResponseTime(LocalDateTime.now());
        response.setStatusCode(200);
        return response;
    }

    public static ExternalSystemResponse error(String systemName, String message, int statusCode) {
        ExternalSystemResponse response = new ExternalSystemResponse();
        response.setSuccess(false);
        response.setSystemName(systemName);
        response.setMessage(message);
        response.setResponseTime(LocalDateTime.now());
        response.setStatusCode(statusCode);
        return response;
    }
}
