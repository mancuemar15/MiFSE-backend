package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Lista;

public interface ServicioLista {
	
	public Lista obtenerPorIdOrdenadoPorNumeroPreferencia(Long id);

    public List<Lista> obtenerListasPorIdResidente(Long idResidente);
    
    public Lista crear(Lista lista);
    
    public Lista actualizar(Lista lista);
    
    void eliminarPorId(Long id);
}
