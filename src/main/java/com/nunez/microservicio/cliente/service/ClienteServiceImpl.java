package com.nunez.microservicio.cliente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nunez.microservicio.cliente.dao.ClienteRepository;
import com.nunez.microservicio.cliente.domain.Cliente;

@Service
public class ClienteServiceImpl {

	@Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
    
    public Cliente findByDni(String dni) {
        return clienteRepository.findByDni(dni).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        validateCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente cliente) {
        cliente.setId(id);
        validateCliente(cliente);
        return clienteRepository.save(cliente);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    private void validateCliente(Cliente cliente) {
        // Lógica adicional de validación si es necesario
    }
}
