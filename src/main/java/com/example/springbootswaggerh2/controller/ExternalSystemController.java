package com.example.springbootswaggerh2.controller;

import com.example.springbootswaggerh2.model.dto.ExternalSystemRequest;
import com.example.springbootswaggerh2.model.dto.ExternalSystemResponse;
import com.example.springbootswaggerh2.service.ExternalSystemAsyncService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/external-systems")
public class ExternalSystemController {

    private static final Logger logger = LoggerFactory.getLogger(ExternalSystemController.class);

    private final ExternalSystemAsyncService asyncService;

    public ExternalSystemController(
            ExternalSystemAsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @PostMapping("/async/send-single")
    public CompletableFuture<ExternalSystemResponse> sendAsyncSingle(
            @RequestBody ExternalSystemRequest request,
            @RequestParam String systemName) {

        logger.info("Recibida solicitud para enviar a {} de forma asíncrona", systemName);
        return asyncService.sendToSystemAsync(request, systemName);
    }
}
