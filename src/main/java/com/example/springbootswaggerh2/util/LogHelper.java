package com.example.springbootswaggerh2.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LogHelper {

    private static final Logger log = LoggerFactory.getLogger(LogHelper.class);

    /**
     * Inicia contexto para una transacción
     */
    public String startTransaction(String operation) {
        String transactionId = "tx-" + UUID.randomUUID().toString().substring(0, 8);

        MDC.put("txId", transactionId);
        MDC.put("operation", operation);

        log.info("Iniciando transacción: {}", operation);
        return transactionId;
    }

    /**
     * Finaliza transacción exitosamente
     */
    public void endTransactionSuccess(long durationMs) {
        MDC.put("status", "SUCCESS");
        MDC.put("durationMs", String.valueOf(durationMs));

        log.info("Transacción completada exitosamente");

        MDC.remove("status");
        MDC.remove("durationMs");
        MDC.clear();
    }

    /**
     * Finaliza transacción con error
     */
    public void endTransactionError(String error, long durationMs) {
        MDC.put("status", "ERROR");
        MDC.put("error", error);
        MDC.put("durationMs", String.valueOf(durationMs));

        log.error("Transacción fallida: {}", error);

        MDC.remove("status");
        MDC.remove("error");
        MDC.remove("durationMs");
        MDC.clear();
    }

    /**
     * Log estructurado básico
     */
    public void logEvent(String eventType, String message, Object... data) {
        MDC.put("eventType", eventType);

        if (data != null && data.length > 0) {
            for (int i = 0; i < data.length; i += 2) {
                if (i + 1 < data.length) {
                    MDC.put(data[i].toString(), data[i + 1].toString());
                }
            }
        }

        log.info(message);

        MDC.remove("eventType");
        // Limpiar datos dinámicos
        if (data != null) {
            for (int i = 0; i < data.length; i += 2) {
                if (i + 1 < data.length) {
                    MDC.remove(data[i].toString());
                }
            }
        }
    }
}