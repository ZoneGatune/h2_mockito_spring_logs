package com.example.springbootswaggerh2.service;

import com.example.springbootswaggerh2.exception.ResourceNotFoundException;
import com.example.springbootswaggerh2.model.Cliente;
import com.example.springbootswaggerh2.model.dto.ClienteDTO;
import com.example.springbootswaggerh2.repository.ClienteRepository;
import com.example.springbootswaggerh2.util.LogHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
    ClienteRepository repository;

    private final LogHelper logHelper;

    private static final Logger structuredLog = LoggerFactory.getLogger("STRUCTURED_LOG");

    public ClienteServiceImpl(LogHelper logHelper) {
        this.logHelper = logHelper;
    }

    @Override
	public Cliente createCliente(Cliente cliente) {
        logHelper.startTransaction("grabando cliente");
        long startTime = System.currentTimeMillis();
        MDC.put("procesando","procesando");
        long duration = System.currentTimeMillis() - startTime;

        logHelper.endTransactionSuccess(duration);
        return repository.save(cliente);
	}

	@Override
	public Cliente getClienteById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employees", "id", "employeeId"));
	}

	@Override
	public List<ClienteDTO> getClientes() {
        List<Cliente> clientes = repository.findAll();

        return clientes.stream()
                .map(cliente -> new ClienteDTO(cliente))
                .collect(Collectors.toList());
	}

    @Override
    public List<Cliente> getCliente() {
        return repository.findAll();
    }

    @Override
	public Cliente updateClienteById(Long id, Cliente cliente) {
        Cliente clienteDetail = this.getClienteById(id);
        clienteDetail.setNombre(cliente.getNombre());
        clienteDetail.setApellidoPaterno(cliente.getApellidoPaterno());
        clienteDetail.setApellidoMaterno(cliente.getApellidoMaterno());
        clienteDetail.setFechaCreacion(new Date());
        clienteDetail.setEstado(true);
		return repository.save(clienteDetail);
	}

	@Override
	public void deleteClienteById(Long id) {
		Cliente cliente = this.getClienteById(id);
		repository.delete(cliente);
	}

}
