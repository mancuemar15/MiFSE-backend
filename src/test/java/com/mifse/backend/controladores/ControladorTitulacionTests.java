package com.mifse.backend.controladores;

import com.mifse.backend.excepciones.TitulacionNotFoundException;
import com.mifse.backend.persistencia.modelos.Titulacion;
import com.mifse.backend.servicios.ServicioTitulacion;
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

@WebMvcTest(ControladorTitulacion.class)
class ControladorTitulacionTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioTitulacion servicioTitulacion;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorTitulacion controladorTitulacion;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorTitulacion).build();
	}

	@Test
	public void obtenerTitulaciones_DeberiaRetornarListaTitulaciones() throws Exception {
		List<Titulacion> titulaciones = new ArrayList<>();

		when(servicioTitulacion.obtenerTodas()).thenReturn(titulaciones);

		mockMvc.perform(get("/titulaciones").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray());
	}

	@Test
    public void obtenerTitulaciones_TitulacionNotFoundException_DeberiaRetornarNotFound() throws Exception {
        when(servicioTitulacion.obtenerTodas()).thenThrow(TitulacionNotFoundException.class);

        mockMvc.perform(get("/titulaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
