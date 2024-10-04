package com.nunez.microservicio.cliente.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.nunez.microservicio.cliente.domain.Cliente;
import com.nunez.microservicio.cliente.service.ClienteServiceImpl;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController<Cuenta> {
	
	

	@Autowired
	private ClienteServiceImpl clienteService;

	@Operation(summary = "Esta operación permite listar clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servicio funcionando correctamente",
                     content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content)
    })
	@GetMapping
	public List<Cliente> getAllClientes() {
		return clienteService.findAll();
	}

	@Operation(summary = "Esta operación permite buscar por idcliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servicio funcionando correctamente",
                     content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content)
    })
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		Cliente cliente = clienteService.findById(id);
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}

	@Operation(summary = "Esta operación permite crear clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación ejecutada correctamente",
                     content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content)
    })
	@PostMapping
	public ResponseEntity<?> createCliente(@RequestBody @Validated Cliente cliente) {

		Cliente clienteBd = clienteService.findByDni(cliente.getDni());
		if (clienteBd != null) {

			return ResponseEntity.ok().body("El cliente con el dni " + cliente.getDni() + " ya existe....");
		} else {

			Cliente createdCliente = clienteService.save(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdCliente);
		}

	}

	@Operation(summary = "Esta operación permite actualizar datos de clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación ejecutada correctamente",
                     content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content)
    })
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody @Validated Cliente cliente) {
		Cliente updatedCliente = clienteService.update(id, cliente);
		return updatedCliente != null ? ResponseEntity.ok(updatedCliente) : ResponseEntity.notFound().build();
	}

	@Operation(summary = "Esta operación permite eliminar clientes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Operación ejecutada correctamente",
                     content = { @Content(mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content)
    })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable("id") Long clienteId) {

		Cliente cliente = clienteService.findById(clienteId);
		if (cliente != null) {
			clienteService.delete(clienteId);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok().body("El cliente no existe");
		}
	}

}
