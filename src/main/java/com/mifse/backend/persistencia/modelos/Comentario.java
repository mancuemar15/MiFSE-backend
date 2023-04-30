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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "comentario")
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_residente")
	@JsonIgnore
	private Residente residente;

	@ManyToOne
	@JoinColumn(name = "ID_centro")
	@JsonIgnore
	private Centro centro;

	@Column(name = "Contenido", nullable = false)
	private String contenido;

	@Column(name = "Valoracion", nullable = false)
	private Double valoracion;

	@Column(name = "Fecha", nullable = false)
	private LocalDateTime fecha;

}