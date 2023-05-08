package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Lista;

public interface ServicioLista {
	
	public Lista obtenerPorIdOrdenadoPorNumeroPreferencia(Integer id);

    public List<Lista> obtenerListasPorIdResidente(Integer idResidente);
    
    public Lista guardar(Lista lista);
    
    public Lista actualizar(Lista lista);
    
    void eliminarPorId(Integer id);
}
