package com.mifse.backend.controladores;

import com.mifse.backend.excepciones.TipoGuardiasFindesFestivosNotFoundException;
import com.mifse.backend.persistencia.modelos.TipoGuardiasFindesFestivos;
import com.mifse.backend.servicios.ServicioTipoGuardiasFindesFestivos;
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

@WebMvcTest(ControladorTipoGuardiasFindesFestivos.class)
class ControladorTipoGuardiasFindesFestivosTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioTipoGuardiasFindesFestivos servicioTipoGuardiasFindesFestivos;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorTipoGuardiasFindesFestivos controladorTipoGuardiasFindesFestivos;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorTipoGuardiasFindesFestivos).build();
	}

	@Test
	public void obtenerEpocasVacaciones_DeberiaRetornarListaTipoGuardiasFindesFestivos() throws Exception {
		List<TipoGuardiasFindesFestivos> tiposGuardiasFindesFestivos = new ArrayList<>();

		when(servicioTipoGuardiasFindesFestivos.obtenerTodos()).thenReturn(tiposGuardiasFindesFestivos);

		mockMvc.perform(get("/tipos-guardias").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray());
	}

	@Test
    public void obtenerEpocasVacaciones_TipoGuardiasFindesFestivosNotFoundException_DeberiaRetornarNotFound() throws Exception {
        when(servicioTipoGuardiasFindesFestivos.obtenerTodos()).thenThrow(new TipoGuardiasFindesFestivosNotFoundException());

        mockMvc.perform(get("/tipos-guardias")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
