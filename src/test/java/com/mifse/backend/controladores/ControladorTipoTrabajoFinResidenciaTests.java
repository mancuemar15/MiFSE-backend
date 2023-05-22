package com.mifse.backend.controladores;

import com.mifse.backend.excepciones.TipoTrabajoFinResidenciaNotFoundException;
import com.mifse.backend.persistencia.modelos.TipoTrabajoFinResidencia;
import com.mifse.backend.servicios.ServicioTipoTrabajoFinResidencia;
import com.mifse.backend.servicios.ServicioUsuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorTipoTrabajoFinResidencia.class)
class ControladorTipoTrabajoFinResidenciaTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioTipoTrabajoFinResidencia servicioTipoTrabajoFinResidencia;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorTipoTrabajoFinResidencia controladorTipoTrabajoFinResidencia;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorTipoTrabajoFinResidencia).build();
	}

	@Test
	public void obtenerTiposTrabajosFinResidencia_DeberiaRetornarListaTipoTrabajoFinResidencia() throws Exception {
		List<TipoTrabajoFinResidencia> tiposTrabajos = new ArrayList<>();

		when(servicioTipoTrabajoFinResidencia.obtenerTodos()).thenReturn(tiposTrabajos);

		mockMvc.perform(get("/tipos-trabajos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray());
	}

	@Test
    public void obtenerTiposTrabajosFinResidencia_TipoTrabajoFinResidenciaNotFoundException_DeberiaRetornarNotFound() throws Exception {
        when(servicioTipoTrabajoFinResidencia.obtenerTodos()).thenThrow(TipoTrabajoFinResidenciaNotFoundException.class);

        mockMvc.perform(get("/tipos-trabajos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
