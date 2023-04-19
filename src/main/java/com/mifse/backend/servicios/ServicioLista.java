package com.mifse.backend.servicios;

import java.util.List;

import com.mifse.backend.persistencia.modelos.Lista;

public interface ServicioLista {

    public List<Lista> obtenerListasPorIdResidente(Integer idResidente);
    
    public Lista guardar(Lista lista);
    
    void eliminarPorId(Integer id);
}
