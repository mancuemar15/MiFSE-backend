package com.mifse.backend.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifse.backend.excepciones.ActualizacionResidenteException;
import com.mifse.backend.excepciones.CreacionResidenteException;
import com.mifse.backend.excepciones.EmailYaExistenteException;
import com.mifse.backend.excepciones.ResidenteNotFoundException;
import com.mifse.backend.persistencia.modelos.Residente;
import com.mifse.backend.servicios.ServicioResidente;
import com.mifse.backend.servicios.ServicioUsuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorResidente.class)
class ControladorResidenteTests {

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ServicioResidente servicioResidente;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorResidente controladorResidente;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorResidente).build();
	}

	@Test
	public void crearResidente_DeberiaRetornarResidenteCreado() throws Exception {
		Residente residente = new Residente();
		String residenteJson = objectMapper.writeValueAsString(residente);
		when(servicioResidente.crear(any(Residente.class))).thenReturn(residente);

		mockMvc.perform(post("/residentes/registro").contentType(MediaType.APPLICATION_JSON).content(residenteJson))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(residente.getId()));
	}

	@Test
	public void crearResidente_EmailYaExistenteException_DeberiaRetornarConflict() throws Exception {

		Residente residente = new Residente();
		String residenteJson = objectMapper.writeValueAsString(residente);
		doThrow(EmailYaExistenteException.class).when(servicioResidente).crear(any(Residente.class));

		mockMvc.perform(post("/residentes/registro").contentType(MediaType.APPLICATION_JSON).content(residenteJson))
				.andExpect(status().isConflict());
	}

	@Test
	public void crearResidente_CreacionResidenteException_DeberiaRetornarInternalServerError() throws Exception {

		Residente residente = new Residente();
		String residenteJson = objectMapper.writeValueAsString(residente);
		doThrow(new CreacionResidenteException()).when(servicioResidente).crear(any(Residente.class));

		mockMvc.perform(post("/residentes/registro").contentType(MediaType.APPLICATION_JSON).content(residenteJson))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void actualizarResidente_DeberiaRetornarResidenteActualizado() throws Exception {

		Residente residente = new Residente();
		String residenteJson = objectMapper.writeValueAsString(residente);
		when(servicioResidente.actualizar(any(Residente.class))).thenReturn(residente);

		mockMvc.perform(put("/residentes").contentType(MediaType.APPLICATION_JSON).content(residenteJson))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(residente.getId()));
	}

	@Test
	public void actualizarResidente_ResidenteNotFoundException_DeberiaRetornarNotFound() throws Exception {

		Residente residente = new Residente();
		String residenteJson = objectMapper.writeValueAsString(residente);
		doThrow(ResidenteNotFoundException.class).when(servicioResidente).actualizar(any(Residente.class));

		mockMvc.perform(put("/residentes").contentType(MediaType.APPLICATION_JSON).content(residenteJson))
				.andExpect(status().isNotFound());
	}

	@Test
	public void actualizarResidente_ActualizacionResidenteException_DeberiaRetornarInternalServerError()
			throws Exception {

		Residente residente = new Residente();
		String residenteJson = objectMapper.writeValueAsString(residente);
		doThrow(new ActualizacionResidenteException()).when(servicioResidente).actualizar(any(Residente.class));

		mockMvc.perform(put("/residentes").contentType(MediaType.APPLICATION_JSON).content(residenteJson))
				.andExpect(status().isInternalServerError());
	}
}
