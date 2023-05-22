package com.mifse.backend.controladores;

import com.mifse.backend.excepciones.TipoResidenteNotFoundException;
import com.mifse.backend.persistencia.modelos.TipoResidente;
import com.mifse.backend.servicios.ServicioTipoResidente;
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

@WebMvcTest(ControladorTipoResidente.class)
class ControladorTipoResidenteTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioTipoResidente servicioTipoResidente;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorTipoResidente controladorTipoResidente;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorTipoResidente).build();
	}

	@Test
	public void obtenerTiposResidentes_DeberiaRetornarListaTipoResidente() throws Exception {
		List<TipoResidente> tiposResidentes = new ArrayList<>();

		when(servicioTipoResidente.obtenerTodos()).thenReturn(tiposResidentes);

		mockMvc.perform(get("/tipos-residentes").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray());
	}

	@Test
    public void obtenerTiposResidentes_TipoResidenteNotFoundException_DeberiaRetornarNotFound() throws Exception {
        when(servicioTipoResidente.obtenerTodos()).thenThrow(new TipoResidenteNotFoundException());

        mockMvc.perform(get("/tipos-residentes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
