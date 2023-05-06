package com.mifse.backend.persistencia.modelos;

import java.util.List;

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

import lombok.Data;

@Data
@Entity
@Table(name = "centro")
public class Centro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@JsonView(Vistas.CentroSimplificado.class)
	private Integer id;

	@Column(name = "Nombre", nullable = false)
	@JsonView(Vistas.CentroSimplificado.class)
	private String nombre;

	@Column(name = "Direccion", nullable = false)
	private String direccion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_localidad", nullable = false)
	private Localidad localidad;

	@Column(name = "URL_imagen")
	private Integer urlImagen;

	@Column(name = "Valoracion_media")
	private Double valoracionMedia;

	@Column(name = "Descripcion", nullable = false)
	private String descripcion;

	@Column(name = "Longitud", nullable = false)
	private Double longitud;

	@Column(name = "Latitud", nullable = false)
	private Double latitud;

	@OneToMany(mappedBy = "centro")
	private List<Comentario> comentarios;
}