package com.example.springbootswaggerh2;


import com.example.springbootswaggerh2.model.Cliente;
import com.example.springbootswaggerh2.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class SpringbootSwaggerH2ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

	@Test
	void contextLoads() {
	}

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }

    @Test
    void fullIntegrationTest() throws Exception {
        // 1. Crear cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellidoPaterno("Pérez");
        cliente.setApellidoMaterno("Pérez");

        String clienteJson = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nombre", is("Juan")))
                .andReturn().getResponse().getContentAsString();

        Cliente created = objectMapper.readValue(clienteJson, Cliente.class);
        Long id = created.getId();

        // 2. Obtener cliente por ID
        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Juan")));

        // 3. Actualizar cliente
        cliente.setNombre("Juan Actualizado");

        mockMvc.perform(put("/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Juan Actualizado")));

        // 4. Obtener todos los clientes
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        // 5. Eliminar cliente
        mockMvc.perform(delete("/clientes/{id}", id))
                .andExpect(status().isOk());

        // 6. Verificar que fue eliminado
        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isNotFound());
    }

}
