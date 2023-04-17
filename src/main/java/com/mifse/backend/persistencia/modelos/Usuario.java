package com.mifse.backend.persistencia.modelos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "Apellido_1")
	private String apellido1;

	@Column(name = "Apellido_2")
	private String apellido2;

	@Column(name = "Email", unique = true)
	private String email;

	@Column(name = "Contrasena")
	private String contrasena;

	@Column(name = "Fecha_alta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAlta;

	@Column(name = "Verificado")
	private boolean verificado;

	@Column(name = "Inhabilitado")
	private boolean inhabilitado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_tipo_usuario")
	private TipoUsuario tipoUsuario;
}
