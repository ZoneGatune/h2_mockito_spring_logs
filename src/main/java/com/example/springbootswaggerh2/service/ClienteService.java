package com.example.springbootswaggerh2.service;

import com.example.springbootswaggerh2.model.Cliente;
import com.example.springbootswaggerh2.model.Employee;

import java.util.List;

public interface ClienteService {
	
	public Cliente createCliente(Cliente cliente);
	public Cliente getClienteById(Long id);
	public List<Cliente> getClientes();
	public Cliente updateClienteById(Long id, Cliente employee);
	public void deleteClienteById(Long id);

}
