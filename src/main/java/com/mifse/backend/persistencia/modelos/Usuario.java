package com.mifse.backend.persistencia.modelos;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.mifse.backend.vistas.Vistas;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8016832135796790816L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({ Vistas.ListaPreferencias.class, Vistas.Centro.class, Vistas.Comentario.class, Vistas.Conversacion.class,
			Vistas.Usuario.class })
	protected Long id;

	@Column(name = "Nombre")
	@JsonView({ Vistas.Conversacion.class, Vistas.Centro.class, Vistas.Comentario.class, Vistas.Conversacion.class,
			Vistas.Usuario.class })
	protected String nombre;

	@Column(name = "Apellido_1")
	@JsonView({ Vistas.Conversacion.class, Vistas.Centro.class, Vistas.Comentario.class, Vistas.Conversacion.class,
			Vistas.Usuario.class })
	protected String apellido1;

	@Column(name = "Apellido_2")
	@JsonView({ Vistas.Conversacion.class, Vistas.Centro.class, Vistas.Comentario.class, Vistas.Conversacion.class,
			Vistas.Usuario.class })
	protected String apellido2;

	@NaturalId
	@Column(name = "Email", unique = true)
	@JsonView(Vistas.Usuario.class)
	protected String email;

	@Column(name = "Contrasena")
	private String contrasena;

	@Column(name = "Fecha_alta")
	private Date fechaAlta;

	@Column(name = "Verificado")
	@JsonView(Vistas.Usuario.class)
	protected boolean verificado;

	@Column(name = "Habilitado")
	@JsonView(Vistas.Usuario.class)
	private boolean habilitado;

	@JsonView(Vistas.Usuario.class)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_tipo_usuario")
	protected TipoUsuario tipoUsuario;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + this.tipoUsuario.getTipo()));
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return this.contrasena;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return this.email;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return this.habilitado;
	}

}
