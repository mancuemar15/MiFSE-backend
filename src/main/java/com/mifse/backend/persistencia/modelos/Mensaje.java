package com.mifse.backend.persistencia.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;

@Data
@Entity
@Table(name = "mensaje")
@JsonView(Vistas.Conversacion.class)
public class Mensaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_usuario_emisor", nullable = false)
	private Residente emisor;

	@ManyToOne
	@JoinColumn(name = "ID_usuario_receptor", nullable = false)
	private Residente receptor;

	@Column(name = "Contenido", nullable = false)
	private String contenido;

	@Column(name = "Fecha_envio", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date fechaEnvio;

	@Column(name = "Leido")
	private Boolean leido;
}
