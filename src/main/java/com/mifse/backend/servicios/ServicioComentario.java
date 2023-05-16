package com.mifse.backend.servicios;

import com.mifse.backend.persistencia.modelos.Comentario;

public interface ServicioComentario {
	
	public Comentario guardar(Comentario comentario);

	public void eliminarPorId(Integer id);

}
