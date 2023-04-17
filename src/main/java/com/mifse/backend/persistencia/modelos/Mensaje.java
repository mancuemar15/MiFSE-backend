package com.mifse.backend.persistencia.modelos;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "mensaje")
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_usuario_emisor", nullable = false)
	private Usuario emisor;

	@ManyToOne
	@JoinColumn(name = "ID_usuario_receptor", nullable = false)
	private Usuario receptor;

	@Column(name = "Contenido", nullable = false, columnDefinition = "TEXT")
	private String contenido;

	@Column(name = "Fecha_envio", nullable = false)
	private LocalDateTime fechaEnvio;

}
