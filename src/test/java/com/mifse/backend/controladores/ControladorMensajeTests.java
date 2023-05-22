package com.mifse.backend.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifse.backend.excepciones.ActualizacionMensajeException;
import com.mifse.backend.excepciones.MensajeNotFoundException;
import com.mifse.backend.persistencia.modelos.Mensaje;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.servicios.ServicioMensaje;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorMensaje.class)
class ControladorMensajeTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@MockBean
	private ServicioMensaje servicioMensaje;

	@InjectMocks
	private ControladorMensaje controladorMensaje;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorMensaje).build();
	}

	@Test
	public void obtenerMensajesPorUsuarioEmisorYUsuarioReceptor_DeberiaRetornarMensajes() throws Exception {
		Long idUsuario1 = 1L;
		Long idUsuario2 = 2L;
		List<Mensaje> mensajes = new ArrayList<>();
		mensajes.add(new Mensaje());
		when(servicioMensaje.obtenerTodosPorIdEmisorYIdReceptor(idUsuario1, idUsuario2)).thenReturn(mensajes);

		mockMvc.perform(get("/mensajes/usuarios/{idUsuario1}/{idUsuario2}", idUsuario1, idUsuario2))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(mensajes.size()));
	}

	@Test
	public void obtenerMensajesPorUsuarioEmisorYUsuarioReceptor_MensajeNotFoundException_DeberiaRetornarNotFound()
			throws Exception {
		Long idUsuario1 = 1L;
		Long idUsuario2 = 2L;
		when(servicioMensaje.obtenerTodosPorIdEmisorYIdReceptor(idUsuario1, idUsuario2))
				.thenThrow(MensajeNotFoundException.class);

		mockMvc.perform(get("/mensajes/usuarios/{idUsuario1}/{idUsuario2}", idUsuario1, idUsuario2))
				.andExpect(status().isNotFound());
	}

	@Test
	public void obtenerUsuariosConMensajesIntercambiados_DeberiaRetornarUsuarios() throws Exception {
		Long idUsuario = 1L;
		Set<Usuario> usuarios = new HashSet<>();
		usuarios.add(new Usuario());
		when(servicioMensaje.obtenerUsuariosConMensajesIntercambiados(idUsuario)).thenReturn(usuarios);

		mockMvc.perform(get("/mensajes/usuarios/{idUsuario}", idUsuario)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(usuarios.size()));
	}

	@Test
	public void obtenerUsuariosConMensajesIntercambiados_MensajeNotFoundException_DeberiaRetornarNotFound()
			throws Exception {
		Long idUsuario = 1L;
		when(servicioMensaje.obtenerUsuariosConMensajesIntercambiados(idUsuario))
				.thenThrow(MensajeNotFoundException.class);

		mockMvc.perform(get("/mensajes/usuarios/{idUsuario}", idUsuario)).andExpect(status().isNotFound());
	}

	@Test
	public void obtenerUsuariosConMensajesIntercambiadosSinLeer_DeberiaRetornarUsuarios() throws Exception {
		Long idUsuario = 1L;
		Set<Usuario> usuarios = new HashSet<>();
		usuarios.add(new Usuario());
		when(servicioMensaje.obtenerUsuariosConMensajesIntercambiadosSinLeer(idUsuario)).thenReturn(usuarios);

		mockMvc.perform(get("/mensajes/usuarios/{idUsuario}/sin-leer", idUsuario)).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(usuarios.size()));
	}

	@Test
	public void obtenerUsuariosConMensajesIntercambiadosSinLeer_MensajeNotFoundException_DeberiaRetornarNotFound()
			throws Exception {
		Long idUsuario = 1L;
		when(servicioMensaje.obtenerUsuariosConMensajesIntercambiadosSinLeer(idUsuario))
				.thenThrow(MensajeNotFoundException.class);

		mockMvc.perform(get("/mensajes/usuarios/{idUsuario}/sin-leer", idUsuario)).andExpect(status().isNotFound());
	}

	@Test
	public void guardarMensaje_DeberiaRetornarMensajeCreado() throws Exception {
		Mensaje mensaje = new Mensaje();
		ObjectMapper objectMapper = new ObjectMapper();
		String mensajeJson = objectMapper.writeValueAsString(mensaje);
		when(servicioMensaje.guardar(mensaje)).thenReturn(mensaje);

		mockMvc.perform(post("/mensajes").contentType(MediaType.APPLICATION_JSON).content(mensajeJson))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(mensaje.getId()));
	}

	@Test
	public void guardarMensaje_MensajeNotFoundException_DeberiaRetornarInternalServerError() throws Exception {
		Mensaje mensaje = new Mensaje();
		ObjectMapper objectMapper = new ObjectMapper();
		String mensajeJson = objectMapper.writeValueAsString(mensaje);
		when(servicioMensaje.guardar(mensaje)).thenThrow(MensajeNotFoundException.class);

		mockMvc.perform(post("/mensajes").contentType(MediaType.APPLICATION_JSON).content(mensajeJson))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void marcarMensajesComoLeidos_DeberiaRetornarMensajesActualizados() throws Exception {
		List<Mensaje> mensajes = new ArrayList<>();
		mensajes.add(new Mensaje());
		doNothing().when(servicioMensaje).marcarComoLeidos(mensajes);

		mockMvc.perform(put("/mensajes/leidos").contentType(MediaType.APPLICATION_JSON).content(asJsonString(mensajes)))
				.andExpect(status().isOk()).andExpect(content().string("Mensajes actualizados"));
	}

	@Test
	public void marcarMensajesComoLeidos_ActualizacionMensajeException_DeberiaRetornarNotModified() throws Exception {
		List<Mensaje> mensajes = new ArrayList<>();
		mensajes.add(new Mensaje());
		doThrow(ActualizacionMensajeException.class).when(servicioMensaje).marcarComoLeidos(mensajes);

		mockMvc.perform(put("/mensajes/leidos").contentType(MediaType.APPLICATION_JSON).content(asJsonString(mensajes)))
				.andExpect(status().isNotModified());
	}

	private String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
