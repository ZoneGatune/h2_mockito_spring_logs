package com.example.springbootswaggerh2;

import com.example.springbootswaggerh2.model.Cliente;
import com.example.springbootswaggerh2.repository.ClienteRepository;
import com.example.springbootswaggerh2.service.ClienteService;
import com.example.springbootswaggerh2.service.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;


import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    void setUp() {
        cliente1 = new Cliente();
        cliente1.setNombre("Juan");
        cliente1.setApellidoPaterno("Pérez");
        cliente1.setApellidoMaterno("Perez");

        cliente2 = new Cliente();
        cliente2.setNombre("María");
        cliente2.setApellidoPaterno("González");
        cliente2.setApellidoMaterno("González");
    }

    @Test
    void findAll_ShouldReturnAllClients() {
        // Given (Arrange)
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        given(clienteRepository.findAll()).willReturn(clientes);

        // When (Act)
        List<Cliente> resultado = clienteService.getClientes();

        // Then (Assert)
        assertThat(resultado).isNotNull();
        assertThat(resultado).hasSize(2);
        assertThat(resultado).contains(cliente1, cliente2);

        // Verificar que el método fue llamado
        then(clienteRepository).should().findAll();
    }
}
