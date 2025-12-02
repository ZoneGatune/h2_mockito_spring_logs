package com.example.springbootswaggerh2;

import com.example.springbootswaggerh2.model.dto.ExternalSystemRequest;
import com.example.springbootswaggerh2.model.dto.ExternalSystemResponse;
import com.example.springbootswaggerh2.service.ExternalSystemAsyncService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@EnableAsync
public class ExternalSystemAsyncServiceTest {

    @InjectMocks
    private ExternalSystemAsyncService asyncService;


    @Test
    void sendToSystemAsync_ShouldReturnSuccessResponse() throws Exception {
        // Given
        ExternalSystemRequest request = new ExternalSystemRequest(
                "CLIENT-123", "datos de prueba", "system-a");

        // When
        CompletableFuture<ExternalSystemResponse> future =
                asyncService.sendToSystemAsync(request, "system-a");

        // Then
        ExternalSystemResponse response = future.get(5, TimeUnit.SECONDS);
        assertThat(response).isNotNull();
        assertThat(response.getSystemName()).isEqualTo("system-a");
    }
}
