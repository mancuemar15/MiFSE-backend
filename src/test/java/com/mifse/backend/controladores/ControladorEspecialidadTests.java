package com.mifse.backend.controladores;

import com.mifse.backend.excepciones.EspecialidadNotFoundException;
import com.mifse.backend.persistencia.modelos.Especialidad;
import com.mifse.backend.servicios.ServicioEspecialidad;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorEspecialidad.class)
class ControladorEspecialidadTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@MockBean
	private ServicioEspecialidad servicioEspecialidad;

	@InjectMocks
	private ControladorEspecialidad controladorEspecialidad;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorEspecialidad).build();
	}

	@Test
	void obtenerEspecialidadesPorIdCentro_DeberiaRetornarListaEspecialidades() throws Exception {
		Long idCentro = 1L;
		List<Especialidad> especialidades = new ArrayList<>();
		Especialidad especialidad1 = new Especialidad();
		especialidad1.setId(1L);
		especialidad1.setNombre("Especialidad 1");
		Especialidad especialidad2 = new Especialidad();
		especialidad2.setId(2L);
		especialidad2.setNombre("Especialidad 2");
		especialidades.add(especialidad1);
		especialidades.add(especialidad2);

		when(servicioEspecialidad.obtenerTodasPorIdCentro(idCentro)).thenReturn(especialidades);

		mockMvc.perform(get("/especialidades/centro/{idCentro}", idCentro)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].nombre").value("Especialidad 1")).andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].nombre").value("Especialidad 2"));

		verify(servicioEspecialidad).obtenerTodasPorIdCentro(idCentro);
	}

	@Test
	void obtenerEspecialidadesPorIdCentro_EspecialidadNotFoundException_DeberiaRetornarNotFound() throws Exception {
		Long idCentro = 1L;

		when(servicioEspecialidad.obtenerTodasPorIdCentro(idCentro)).thenThrow(EspecialidadNotFoundException.class);

		mockMvc.perform(get("/especialidades/centro/{idCentro}", idCentro)).andExpect(status().isNotFound());

		verify(servicioEspecialidad).obtenerTodasPorIdCentro(idCentro);
	}
}
