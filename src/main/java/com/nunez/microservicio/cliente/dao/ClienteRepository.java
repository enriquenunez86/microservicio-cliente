package com.nunez.microservicio.cliente.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nunez.microservicio.cliente.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	public Optional<Cliente> findByDni(String dni);
	
}	