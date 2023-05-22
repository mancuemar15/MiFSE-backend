package com.mifse.backend.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifse.backend.excepciones.*;
import com.mifse.backend.persistencia.modelos.Usuario;
import com.mifse.backend.persistencia.modelos.dto.Credenciales;
import com.mifse.backend.security.GeneradorToken;
import com.mifse.backend.servicios.ServicioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorUsuario.class)
class ControladorUsuarioTests {

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@MockBean
	private GeneradorToken generadorToken;

	@MockBean
	private DaoAuthenticationProvider daoAuthenticationProvider;

	@InjectMocks
	private ControladorUsuario controladorUsuario;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorUsuario).build();
	}

	@Test
	public void obtenerTodosUsuarios_DeberiaRetornarListaUsuarios() throws Exception {
		List<Usuario> usuarios = new ArrayList<>();

		when(servicioUsuario.obtenerTodos()).thenReturn(usuarios);

		mockMvc.perform(get("/usuarios").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$").isArray());
	}

	@Test
    public void obtenerTodosUsuarios_UsuarioNotFoundException_DeberiaRetornarNotFound() throws Exception {
        when(servicioUsuario.obtenerTodos()).thenThrow(UsuarioNotFoundException.class);

        mockMvc.perform(get("/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	@Test
	public void verificarCorreoElectronico_DeberiaRetornarOk() throws Exception {
		Long id = 1L;

		mockMvc.perform(
				get("/usuarios/verificar").param("id", String.valueOf(id)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void verificarCorreoElectronico_VerificacionUsuarioException_DeberiaRetornarBadRequest() throws Exception {
		Long id = 1L;

		doThrow(VerificacionUsuarioException.class).doNothing().when(servicioUsuario).verificarUsuario(id);

		mockMvc.perform(
				get("/usuarios/verificar").param("id", String.valueOf(id)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void iniciarSesionUsuario_CredencialesValidas_DeberiaRetornarUsuarioConToken() throws Exception {
		String email = "test@example.com";
		String contrasena = "password";

		Credenciales credenciales = new Credenciales();
		credenciales.setEmail(email);
		credenciales.setContrasena(contrasena);

		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setEmail(email);

		Map<String, Object> usuarioMap = new HashMap<>();
		usuarioMap.put("id", usuario.getId());
		usuarioMap.put("email", usuario.getEmail());

		String token = "mock_token";

		when(daoAuthenticationProvider.authenticate(any(UsernamePasswordAuthenticationToken.class)))
				.thenReturn(new UsernamePasswordAuthenticationToken(usuario, null));
		when(generadorToken.generarToken(any(Authentication.class))).thenReturn(token);

		mockMvc.perform(post("/usuarios/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(credenciales))).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.token").value(token));
	}

	@Test
	public void iniciarSesionUsuario_CredencialesInvalidas_DeberiaRetornarUnauthorized() throws Exception {
		String email = "test@example.com";
		String contrasena = "password";

		Credenciales credenciales = new Credenciales();
		credenciales.setEmail(email);
		credenciales.setContrasena(contrasena);

		when(daoAuthenticationProvider.authenticate(any(UsernamePasswordAuthenticationToken.class)))
				.thenThrow(BadCredentialsException.class);

		mockMvc.perform(post("/usuarios/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(credenciales))).andExpect(status().isUnauthorized());
	}

	@Test
	public void iniciarSesionUsuario_ErrorEnElServidor_DeberiaRetornarInternalServerError() throws Exception {
		String email = "test@example.com";
		String contrasena = "password";

		Credenciales credenciales = new Credenciales();
		credenciales.setEmail(email);
		credenciales.setContrasena(contrasena);

		when(daoAuthenticationProvider.authenticate(any(UsernamePasswordAuthenticationToken.class)))
				.thenThrow(RuntimeException.class);

		mockMvc.perform(post("/usuarios/login").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(credenciales))).andExpect(status().isInternalServerError());
	}

	@Test
	public void bloquearUsuario_UsuarioExistente_DeberiaRetornarOk() throws Exception {
		Long id = 1L;

		mockMvc.perform(put("/usuarios/bloquear/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void bloquearUsuario_BloqueoUsuarioException_DeberiaRetornarInternalServerError() throws Exception {
		Long id = 1L;

		doThrow(BloqueoUsuarioException.class).doNothing().when(servicioUsuario).bloquear(id);

		mockMvc.perform(put("/usuarios/bloquear/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void desbloquearUsuario_UsuarioExistente_DeberiaRetornarOk() throws Exception {
		Long id = 1L;

		mockMvc.perform(put("/usuarios/desbloquear/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void desbloquearUsuario_DesbloqueoUsuarioException_DeberiaRetornarInternalServerError() throws Exception {
		Long id = 1L;

		doThrow(DesbloqueoUsuarioException.class).doNothing().when(servicioUsuario).desbloquear(id);

		mockMvc.perform(put("/usuarios/desbloquear/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void borrarUsuario_UsuarioExistente_DeberiaRetornarOk() throws Exception {
		Long id = 1L;
		String contrasena = "password";

		Map<String, String> credenciales = new HashMap<>();
		credenciales.put("contrasena", contrasena);

		mockMvc.perform(delete("/usuarios/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(credenciales))).andExpect(status().isOk());
	}

	@Test
	public void borrarUsuario_AutenticacionFallida_DeberiaRetornarUnauthorized() throws Exception {
		Long id = 1L;
		String contrasena = "password";

		Map<String, String> credenciales = new HashMap<>();
		credenciales.put("contrasena", contrasena);

		doThrow(BadCredentialsException.class).doNothing().when(servicioUsuario).eliminar(eq(id), eq(contrasena));

		mockMvc.perform(delete("/usuarios/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(credenciales))).andExpect(status().isUnauthorized());
	}

	@Test
	public void borrarUsuario_EliminacionUsuarioException_DeberiaRetornarInternalServerError() throws Exception {
		Long id = 1L;
		String contrasena = "password";

		Map<String, String> credenciales = new HashMap<>();
		credenciales.put("contrasena", contrasena);

		doThrow(EliminacionUsuarioException.class).doNothing().when(servicioUsuario).eliminar(eq(id), eq(contrasena));

		mockMvc.perform(delete("/usuarios/{id}", id).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(credenciales))).andExpect(status().isInternalServerError());
	}
}
