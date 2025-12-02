package com.example.springbootswaggerh2.controller;


import com.example.springbootswaggerh2.model.Cliente;
import com.example.springbootswaggerh2.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Api(value = "Cliente service", description = "Service to manage Clientes")
public class ClienteController {
	
	@Autowired
    ClienteService service;
	
	@PostMapping()
	@ApiOperation(value = "Create Cliente", response = Cliente.class)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiResponses(value = {
			@ApiResponse(code = 201, response = Cliente.class, message = "Cliente created successfully"),
			@ApiResponse(code = 400, message = "Invalid Cliente request"),
			@ApiResponse(code = 401, message = "You are not authorized to access the resource"),
			@ApiResponse(code = 403, message = "You are forbidden to create Cliente"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	public Cliente createCliente(@Valid @RequestBody Cliente cliente,@RequestHeader("consumerId") String consumerId,
                                 @RequestHeader("traceparent") String traceparent,
                                 @RequestHeader("deviceType") String deviceType,
                                 @RequestHeader("deviceId") String deviceId) {
		return service.createCliente(cliente);
	}
	
	@GetMapping()
	@ApiOperation(value = "View list of clientes", response = Cliente.class, responseContainer = "List" )
	@ResponseStatus(value = HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved Cliente list", response = Cliente.class, responseContainer = "List"),
			@ApiResponse(code = 401, message = "You are not authorized to access the resource"),
			@ApiResponse(code = 403, message = "You are forbidden to view Cliente list"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	public List<Cliente> getClientes() {
		return service.getClientes();
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "View Cliente details by id", response = Cliente.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Cliente.class, message = "Successfully retrieved Cliente by id"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 401, message = "You are not authorized to access the resource"),
			@ApiResponse(code = 403, message = "You are forbidden to view Cliente by id"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	public Cliente getClienteById(@PathVariable(value = "id") Long id) {
		return service.getClienteById(id);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Update Cliente details by id", response = Cliente.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Cliente.class, message = "Successfully updated Cliente by id"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 401, message = "You are not authorized to access the resource"),
			@ApiResponse(code = 403, message = "You are forbidden to update Cliente by id"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	public Cliente updateClienteById(@Valid @RequestBody Cliente cliente, @PathVariable(value = "id") Long id) {
		return service.updateClienteById(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete Cliente details by id", response = Cliente.class)
	@ResponseStatus(value = HttpStatus.OK)
	@ApiResponses(value = {
			@ApiResponse(code = 200, response = Cliente.class, message = "Successfully deleted Cliente by id"),
			@ApiResponse(code = 400, message = "Invalid request"),
			@ApiResponse(code = 401, message = "You are not authorized to access the resource"),
			@ApiResponse(code = 403, message = "You are forbidden to delete Cliente by id"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	public ResponseEntity<?> deleteClienteById(@PathVariable(value = "id") Long id) {
		service.deleteClienteById(id);
		return ResponseEntity.ok().build();
	}

}
