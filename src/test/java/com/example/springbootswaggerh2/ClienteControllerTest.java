package com.example.springbootswaggerh2;

import com.example.springbootswaggerh2.controller.ClienteController;
import com.example.springbootswaggerh2.model.Cliente;
import com.example.springbootswaggerh2.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAll_ShouldReturnClients() throws Exception {
        // Given
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setApellidoPaterno("Pérez");
        cliente1.setApellidoMaterno("Perez");

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("María");
        cliente2.setApellidoPaterno("González");
        cliente2.setApellidoMaterno("González");

        given(clienteService.getClientes()).willReturn(Arrays.asList(cliente1, cliente2));

        // When & Then
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Juan")))
                .andExpect(jsonPath("$[1].nombre", is("María")));
    }
}
