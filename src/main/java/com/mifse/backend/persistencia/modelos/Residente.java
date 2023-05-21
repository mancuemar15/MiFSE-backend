package com.mifse.backend.persistencia.modelos;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "residente")
@PrimaryKeyJoinColumn(name = "ID")
public class Residente extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6610378539267640777L;

	@Id
	@JsonView({ Vistas.ListaPreferencias.class, Vistas.ListaExtendida.class, Vistas.Conversacion.class,
			Vistas.Centro.class, Vistas.Comentario.class, Vistas.Usuario.class })
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_tipo_residente")
	@JsonView({ Vistas.Conversacion.class, Vistas.Centro.class, Vistas.Comentario.class })
	private TipoResidente tipoResidente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_titulacion")
	@JsonView({ Vistas.ListaPreferencias.class, Vistas.Conversacion.class, Vistas.Centro.class,
			Vistas.Comentario.class })
	private Titulacion titulacion;

}
