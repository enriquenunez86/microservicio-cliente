package com.nunez.microservicio.cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
	    info = @Info(
	        title = "Microservicio de Clientes",
	        version = "1.0.0", // Establece la versión de la especificación OpenAPI
	        description = "Este microservicio gestiona las operaciones relacionadas con los clientes, como la creación, consulta y actualización de clientes."
	    )
	    
	)
public class MicroservicioClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioClienteApplication.class, args);
	}

}
