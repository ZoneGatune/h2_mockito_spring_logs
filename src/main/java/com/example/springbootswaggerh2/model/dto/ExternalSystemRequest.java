package com.example.springbootswaggerh2.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExternalSystemRequest {

    private String clientId;
    private String data;
    private String systemName;
    private LocalDateTime timestamp;

    public ExternalSystemRequest(String clientId, String data, String systemName) {
        this.clientId = clientId;
        this.data = data;
        this.systemName = systemName;
        this.timestamp = LocalDateTime.now();
    }

}

