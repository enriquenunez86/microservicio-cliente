package com.nunez.microservicio.cliente.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data

@Schema(description = "Representa a un cliente en el sistema, con información personal básica como nombre, apellido, DNI y correo electrónico.")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Identificador único del cliente en la base de datos", example = "1", type = "integer", format = "int64")
	private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre del cliente", example = "Juan", required = true)
	private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Schema(description = "Apellido del cliente", example = "Pérez", required = true)
	private String apellido;

	@NotBlank(message = "El DNI es obligatorio")
	@Schema(description = "Documento Nacional de Identidad del cliente (DNI)", pattern = "^[0-9]{8}$", example = "12345678", required = true)
	@Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe tener 8 dígitos")
	private String dni;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Schema(description = "Correo electrónico del cliente", example = "juan.perez@example.com", required = true)
	private String email;

	// Getters y setters
	// ...
}
