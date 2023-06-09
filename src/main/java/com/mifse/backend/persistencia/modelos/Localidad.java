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
import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;

@Data
@Entity
@Table(name = "localidad")
public class Localidad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "Codigo_postal")
	private Integer codigoPostal;

	@Column(name = "Nombre")
	@JsonView(Vistas.Centro.class)
	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_provincia", nullable = false)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonView({Vistas.Lista.class, Vistas.ListaExtendida.class, Vistas.Centro.class})
	private Provincia provincia;

}
