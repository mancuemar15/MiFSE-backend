package com.mifse.backend.controladores;

import com.mifse.backend.excepciones.EmailException;
import com.mifse.backend.persistencia.modelos.dto.FormularioContacto;
import com.mifse.backend.servicios.ServicioEmail;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorContacto.class)
class ControladorContactoTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioEmail servicioEmail;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorContacto controladorContacto;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorContacto).build();
	}

	@Test
	void enviarFormularioContacto_DeberiaRetornarOK() throws Exception {
		FormularioContacto formulario = new FormularioContacto();
		formulario.setNombre("John Doe");
		formulario.setEmail("johndoe@example.com");
		formulario.setAsunto("Consulta");
		formulario.setMensaje("Hola, tengo una pregunta.");

		mockMvc.perform(post("/contacto").contentType(MediaType.APPLICATION_JSON).content(
				"{\"nombre\":\"John Doe\",\"email\":\"johndoe@example.com\",\"asunto\":\"Consulta\",\"mensaje\":\"Hola, tengo una pregunta.\"}"))
				.andExpect(status().isOk());

		verify(servicioEmail).enviarEmailContacto(formulario.getEmail(), formulario.getAsunto(),
				"Nombre: " + formulario.getNombre() + "\n" + "Mensaje: " + formulario.getMensaje());
	}

	@Test
	void enviarFormularioContacto_EmailException_DeberiaRetornarInternalServerError() throws Exception {
		FormularioContacto formulario = new FormularioContacto();
		formulario.setNombre("John Doe");
		formulario.setEmail("johndoe@example.com");
		formulario.setAsunto("Consulta");
		formulario.setMensaje("Hola, tengo una pregunta.");

		doThrow(EmailException.class).when(servicioEmail).enviarEmailContacto(anyString(), anyString(), anyString());

		mockMvc.perform(post("/contacto").contentType(MediaType.APPLICATION_JSON).content(
				"{\"nombre\":\"John Doe\",\"email\":\"johndoe@example.com\",\"asunto\":\"Consulta\",\"mensaje\":\"Hola, tengo una pregunta.\"}"))
				.andExpect(status().isInternalServerError());

		verify(servicioEmail).enviarEmailContacto(formulario.getEmail(), formulario.getAsunto(),
				"Nombre: " + formulario.getNombre() + "\n" + "Mensaje: " + formulario.getMensaje());
	}
}
