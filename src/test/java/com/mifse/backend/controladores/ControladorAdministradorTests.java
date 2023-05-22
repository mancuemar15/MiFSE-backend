package com.mifse.backend.controladores;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifse.backend.excepciones.AdministradorNotFoundException;
import com.mifse.backend.excepciones.CreacionAdministradorException;
import com.mifse.backend.excepciones.EmailYaExistenteException;
import com.mifse.backend.persistencia.modelos.Administrador;
import com.mifse.backend.servicios.ServicioAdministrador;
import com.mifse.backend.servicios.ServicioUsuario;

@WebMvcTest(ControladorAdministrador.class)
class ControladorAdministradorTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioAdministrador servicioAdministrador;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorAdministrador controladorAdministrador;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorAdministrador).build();
	}

	@Test
	public void crearAdministrador_DeberiaRetornarAdministradorCreado() throws Exception {
		Administrador administrador = new Administrador();
		administrador.setId(1L);
		administrador.setNombre("Admin");
		administrador.setEmail("admin@example.com");

		when(servicioAdministrador.crear(any(Administrador.class))).thenReturn(administrador);

		mockMvc.perform(post("/administradores/registro").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(administrador))).andExpect(status().isCreated());

		verify(servicioAdministrador).crear(any(Administrador.class));
	}

	@Test
	public void crearAdministrador_ConEmailExistente_DeberiaRetornarConflict() throws Exception {
		Administrador administrador = new Administrador();
		administrador.setId(1L);
		administrador.setNombre("Admin");
		administrador.setEmail("admin@example.com");

		doThrow(EmailYaExistenteException.class).when(servicioAdministrador).crear(any(Administrador.class));

		mockMvc.perform(post("/administradores/registro").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(administrador))).andExpect(status().isConflict());

		verify(servicioAdministrador).crear(any(Administrador.class));
	}

	@Test
	public void crearAdministrador_ConErrorEnCreacion_DeberiaRetornarInternalServerError() throws Exception {
		Administrador administrador = new Administrador();
		administrador.setId(1L);
		administrador.setNombre("Admin");
		administrador.setEmail("admin@example.com");

		doThrow(CreacionAdministradorException.class).when(servicioAdministrador).crear(any(Administrador.class));

		mockMvc.perform(post("/administradores/registro").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(administrador))).andExpect(status().isInternalServerError());

		verify(servicioAdministrador).crear(any(Administrador.class));
	}

	@Test
	public void actualizarAdministrador_DeberiaRetornarAdministradorActualizado() throws Exception {
		Administrador administrador = new Administrador();
		administrador.setId(1L);
		administrador.setNombre("Admin");
		administrador.setEmail("admin@example.com");

		when(servicioAdministrador.actualizar(any(Administrador.class))).thenReturn(administrador);

		mockMvc.perform(put("/administradores").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(administrador))).andExpect(status().isOk());

		verify(servicioAdministrador).actualizar(any(Administrador.class));
	}

	@Test
	public void actualizarAdministrador_ConAdministradorNoEncontrado_DeberiaRetornarNotFound() throws Exception {
		Administrador administrador = new Administrador();
		administrador.setId(1L);
		administrador.setNombre("Admin");
		administrador.setEmail("admin@example.com");

		doThrow(AdministradorNotFoundException.class).when(servicioAdministrador).actualizar(any(Administrador.class));

		mockMvc.perform(put("/administradores").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(administrador))).andExpect(status().isNotFound());

		verify(servicioAdministrador).actualizar(any(Administrador.class));
	}
}