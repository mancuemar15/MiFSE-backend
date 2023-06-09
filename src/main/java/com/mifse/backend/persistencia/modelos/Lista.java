package com.mifse.backend.persistencia.modelos;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lista")
public class Lista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@JsonView({Vistas.ListaPreferencias.class, Vistas.ListaExtendida.class})
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_residente", nullable = false)
	@JsonView({Vistas.ListaPreferencias.class, Vistas.ListaExtendida.class})
	private Residente residente;

	@Column(name = "Nombre")
	@JsonView({Vistas.ListaPreferencias.class, Vistas.ListaExtendida.class})
	private String nombre;

	@Column(name = "Fecha_creacion")
	@JsonView({Vistas.ListaPreferencias.class, Vistas.ListaExtendida.class})
	private Date fechaCreacion;

	@Column(name = "Fecha_actualizacion")
	@JsonView({Vistas.ListaPreferencias.class, Vistas.ListaExtendida.class})
	private Date fechaActualizacion;

	@OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonView({Vistas.ListaPreferencias.class, Vistas.ListaExtendida.class})
	private List<Preferencia> preferencias;

}