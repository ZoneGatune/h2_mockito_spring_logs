package com.example.springbootswaggerh2.service;

import com.example.springbootswaggerh2.model.dto.ExternalSystemRequest;
import com.example.springbootswaggerh2.model.dto.ExternalSystemResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.CompletableFuture;

@Service
public class ExternalSystemAsyncService {

    private static final Logger logger = LoggerFactory.getLogger(ExternalSystemAsyncService.class);

    /**
     * Envía datos a un sistema externo de forma asíncrona
     */
    @Async
    public CompletableFuture<ExternalSystemResponse> sendToSystemAsync(
            ExternalSystemRequest request,
            String systemName) {

        logger.info("Iniciando envío asíncrono a {} para cliente {}",
                systemName, request.getClientId());

        try {
            // Simulación de delay de red
            simulateNetworkDelay();

            // Simulación de llamada HTTP a API externa
            boolean success = simulateExternalApiCall(systemName, request.getData());

            if (success) {
                logger.info("Envío exitoso a {} para cliente {}",
                        systemName, request.getClientId());
                return CompletableFuture.completedFuture(
                        ExternalSystemResponse.success(systemName,
                                "Datos enviados correctamente a " + systemName)
                );
            } else {
                logger.warn("Error en envío a {} para cliente {}",
                        systemName, request.getClientId());
                return CompletableFuture.completedFuture(
                        ExternalSystemResponse.error(systemName,
                                "Error al enviar datos", 500)
                );
            }

        } catch (Exception e) {
            logger.error("Error inesperado enviando a {}: {}", systemName, e.getMessage());
            return CompletableFuture.completedFuture(
                    ExternalSystemResponse.error(systemName,
                            "Error interno: " + e.getMessage(), 500)
            );
        }
    }

    private void simulateNetworkDelay() {
        try {
            // Simular delay entre 500ms y 3000ms
            int delay = 500 + (int)(Math.random() * 2500);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Envío interrumpido", e);
        }
    }

    private boolean simulateExternalApiCall(String systemName, String data) {
        // Simulación de éxito/error aleatorio
        double successRate = 0.8; // 80% éxito

        // Sistema C siempre falla (simulado como deshabilitado)
        if ("system-c".equals(systemName)) {
            return false;
        }

        return Math.random() < successRate;
    }
}
