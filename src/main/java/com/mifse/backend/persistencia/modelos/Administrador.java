package com.mifse.backend.persistencia.modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Administrador extends Usuario {

	private static final long serialVersionUID = 7307864758959623460L;
	
	@Id
	private Long id;
}
