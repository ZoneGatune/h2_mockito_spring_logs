package com.example.springbootswaggerh2.model.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BatchSendResult {

    private int totalSystems;
    private int successfulSends;
    private int failedSends;
    private List<ExternalSystemResponse> responses;
    private LocalDateTime completionTime;

    public BatchSendResult() {
        this.responses = new ArrayList<>();
    }
}
