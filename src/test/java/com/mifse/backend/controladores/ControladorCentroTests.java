package com.mifse.backend.controladores;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifse.backend.excepciones.CentroNotFoundException;
import com.mifse.backend.persistencia.modelos.Centro;
import com.mifse.backend.servicios.ServicioCentro;
import com.mifse.backend.servicios.ServicioUsuario;
import com.mifse.backend.vistas.Vistas;

@WebMvcTest(ControladorCentro.class)
class ControladorCentroTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioCentro servicioCentro;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorCentro controladorCentro;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorCentro).build();
	}

	@Test
	public void obtenerCentroPorId_CentroExistente_DeberiaRetornarCentro() throws Exception {
		Long id = 1L;
		Centro centro = new Centro();
		centro.setId(id);
		centro.setNombre("Centro de prueba");

		when(servicioCentro.obtenerPorId(id)).thenReturn(centro);

		MvcResult result = mockMvc.perform(get("/centros/{id}", id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String responseBody = result.getResponse().getContentAsString();
		String expectedResponseBody = new ObjectMapper().writeValueAsString(centro);
		assertEquals(expectedResponseBody, responseBody);

		verify(servicioCentro).obtenerPorId(id);
	}

	@Test
	public void obtenerCentroPorId_CentroNoEncontrado_DeberiaLanzarNotFound() throws Exception {
		Long id = 1L;

		when(servicioCentro.obtenerPorId(id)).thenThrow(CentroNotFoundException.class);

		mockMvc.perform(get("/centros/{id}", id).accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andReturn();

		verify(servicioCentro).obtenerPorId(id);
	}

	@Test
	public void obtenerCentrosPorNombre_CentrosEncontrados_DeberiaRetornarCentros() throws Exception {
		String nombre = "Centro";
		Centro centro1 = new Centro();
		centro1.setId(1L);
		centro1.setNombre("Centro 1");
		Centro centro2 = new Centro();
		centro2.setId(2L);
		centro2.setNombre("Centro 2");
		List<Centro> centros = new ArrayList<>();
		centros.add(centro1);
		centros.add(centro2);

		when(servicioCentro.obtenerPorNombre(nombre)).thenReturn(centros);

		MvcResult result = mockMvc.perform(get("/centros/buscar/{nombre}", nombre).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		String responseBody = result.getResponse().getContentAsString();
		String expectedResponseBody = new ObjectMapper().writerWithView(Vistas.CentroReducido.class)
				.writeValueAsString(centros);
		assertEquals(expectedResponseBody, responseBody);

		verify(servicioCentro).obtenerPorNombre(nombre);
	}

	@Test
	public void obtenerCentrosPorNombre_CentrosNoEncontrados_DeberiaLanzarNotFound() throws Exception {
		String nombre = "Centro";

		doThrow(CentroNotFoundException.class).when(servicioCentro).obtenerPorNombre(nombre);

		mockMvc.perform(get("/centros/buscar/{nombre}", nombre).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andReturn();

		verify(servicioCentro).obtenerPorNombre(nombre);
	}
}