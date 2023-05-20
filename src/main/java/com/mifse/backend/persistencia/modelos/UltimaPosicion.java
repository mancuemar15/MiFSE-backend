package com.mifse.backend.persistencia.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
@Table(name = "ultima_posicion")
public class UltimaPosicion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "Numero")
	private Integer numero;

	@Column(name = "Ano")
	private Integer ano;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_especialidad")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Especialidad especialidad;

}