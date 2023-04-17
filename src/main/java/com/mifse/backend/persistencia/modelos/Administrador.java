package com.mifse.backend.persistencia.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;

	@OneToOne
	@JoinColumn(name = "ID")
	private Usuario usuario;
}
