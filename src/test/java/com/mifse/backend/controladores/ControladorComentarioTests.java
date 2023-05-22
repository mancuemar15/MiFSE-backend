package com.mifse.backend.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifse.backend.excepciones.ComentarioNotFoundException;
import com.mifse.backend.excepciones.EliminacionComentarioException;
import com.mifse.backend.excepciones.GuardarComentarioException;
import com.mifse.backend.persistencia.modelos.Comentario;
import com.mifse.backend.servicios.ServicioComentario;
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

@WebMvcTest(ControladorComentario.class)
class ControladorComentarioTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioComentario servicioComentario;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorComentario controladorComentario;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorComentario).build();
	}

	@Test
	void guardar_DeberiaRetornarComentarioCreado() throws Exception {
		Comentario comentario = new Comentario();
		comentario.setId(1L);
		comentario.setContenido("¡Hola!");

		when(servicioComentario.guardar(any(Comentario.class))).thenReturn(comentario);

		mockMvc.perform(post("/comentarios").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comentario))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(comentario.getId()))
				.andExpect(jsonPath("$.contenido").value(comentario.getContenido())).andReturn();

		verify(servicioComentario).guardar(any(Comentario.class));
	}

	@Test
	void guardar_GuardarComentarioException_DeberiaRetornarInternalServerError() throws Exception {
		Comentario comentario = new Comentario();
		comentario.setId(1L);
		comentario.setContenido("¡Hola!");

		when(servicioComentario.guardar(any(Comentario.class))).thenThrow(GuardarComentarioException.class);

		mockMvc.perform(post("/comentarios").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(comentario)))
				.andExpect(status().isInternalServerError());

		verify(servicioComentario).guardar(any(Comentario.class));
	}

	@Test
	void borrar_ComentarioEncontrado_DeberiaRetornarNoContent() throws Exception {
		Long comentarioId = 1L;

		mockMvc.perform(delete("/comentarios/{id}", comentarioId)).andExpect(status().isNoContent());

		verify(servicioComentario).eliminarPorId(comentarioId);
	}

	@Test
	void borrar_EliminacionComentarioException_DeberiaRetornarInternalServerError() throws Exception {
		Long comentarioId = 1L;

		doThrow(new EliminacionComentarioException(comentarioId)).when(servicioComentario).eliminarPorId(comentarioId);

		mockMvc.perform(delete("/comentarios/{id}", comentarioId)).andExpect(status().isInternalServerError());

		verify(servicioComentario).eliminarPorId(comentarioId);
	}

	@Test
	void borrar_ComentarioNotFoundException_DeberiaRetornarNotFound() throws Exception {
		Long comentarioId = 1L;

		doThrow(ComentarioNotFoundException.class).when(servicioComentario).eliminarPorId(comentarioId);

		mockMvc.perform(delete("/comentarios/{id}", comentarioId)).andExpect(status().isNotFound());

		verify(servicioComentario).eliminarPorId(comentarioId);
	}
}