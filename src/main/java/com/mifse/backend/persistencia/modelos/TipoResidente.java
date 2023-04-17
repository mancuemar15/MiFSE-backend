package com.mifse.backend.persistencia.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tipo_residente")
public class TipoResidente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}