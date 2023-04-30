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
@Table(name = "lista")
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ID_residente", nullable = false)
	@JsonIgnore
	private Residente residente;

	@Column(name = "Fecha_creacion", nullable = false)
	private LocalDateTime fechaCreacion;

	@Column(name = "Fecha_actualizacion", nullable = false)
	private LocalDateTime fechaActualizacion;

}