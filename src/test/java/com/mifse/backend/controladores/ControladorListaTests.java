package com.mifse.backend.controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mifse.backend.excepciones.*;
import com.mifse.backend.persistencia.modelos.Lista;
import com.mifse.backend.servicios.ServicioLista;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorLista.class)
class ControladorListaTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioLista servicioLista;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorLista controladorLista;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorLista).build();
	}

	@Test
	void obtenerListaPorId_ListaExistente_DebeRetornarListaConHttpStatusOK() throws Exception {
		Lista lista = new Lista();
		lista.setId(1L);

		when(servicioLista.obtenerPorIdOrdenadoPorNumeroPreferencia(anyLong())).thenReturn(lista);

		mockMvc.perform(get("/listas/{id}", 1L)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(1));
	}

	@Test
    void obtenerListaPorId_ListaNoEncontrada_DebeRetornarHttpStatusNoContent() throws Exception {
        when(servicioLista.obtenerPorIdOrdenadoPorNumeroPreferencia(anyLong())).thenThrow(ListaNotFoundException.class);

        mockMvc.perform(get("/listas/{id}", 1L))
                .andExpect(status().isNoContent());
    }

	@Test
	void obtenerListaPreferenciasPorId_ListaExistente_DebeRetornarListaConHttpStatusOK() throws Exception {
		Lista lista = new Lista();
		lista.setId(1L);

		when(servicioLista.obtenerPorIdOrdenadoPorNumeroPreferencia(anyLong())).thenReturn(lista);

		mockMvc.perform(get("/listas/{id}/preferencias", 1L)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").value(1));
	}

	@Test
    void obtenerListaPreferenciasPorId_ListaNoEncontrada_DebeRetornarHttpStatusNoContent() throws Exception {
        when(servicioLista.obtenerPorIdOrdenadoPorNumeroPreferencia(anyLong())).thenThrow(ListaNotFoundException.class);

        mockMvc.perform(get("/listas/{id}/preferencias", 1L))
                .andExpect(status().isNoContent());
    }

	@Test
	void obtenerListasDeResidente_ListasEncontradas_DebeRetornarListasConHttpStatusOK() throws Exception {
		Lista lista1 = new Lista();
		lista1.setId(1L);
		Lista lista2 = new Lista();
		lista2.setId(2L);
		List<Lista> listas = Arrays.asList(lista1, lista2);

		when(servicioLista.obtenerListasPorIdResidente(anyLong())).thenReturn(listas);

		mockMvc.perform(get("/listas/residente/{idResidente}", 1L)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[1].id").value(2));
	}

	@Test
    void obtenerListasDeResidente_ListasNoEncontradas_DebeRetornarHttpStatusNoContent() throws Exception {
        when(servicioLista.obtenerListasPorIdResidente(anyLong())).thenThrow(ListaNotFoundException.class);

        mockMvc.perform(get("/listas/residente/{idResidente}", 1L))
                .andExpect(status().isNoContent());
    }

	@Test
	void crearLista_CreacionExitosa_DebeRetornarListaCreadaConHttpStatusCreated() throws Exception {
		Lista lista = new Lista();
		lista.setId(1L);

		when(servicioLista.crear(any())).thenReturn(lista);

		mockMvc.perform(post("/listas").contentType(MediaType.APPLICATION_JSON).content(asJsonString(lista)))
				.andExpect(status().isCreated()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	void crearLista_CreacionFallida_DebeRetornarHttpStatusInternalServerError() throws Exception {
		doThrow(CreacionListaException.class).when(servicioLista).crear(any());

		mockMvc.perform(post("/listas").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Lista())))
				.andExpect(status().isInternalServerError());
	}

	@Test
	void actualizarLista_ActualizacionExitosa_DebeRetornarListaActualizadaConHttpStatusOK() throws Exception {
		Lista lista = new Lista();
		lista.setId(1L);

		when(servicioLista.actualizar(any())).thenReturn(lista);

		mockMvc.perform(put("/listas").contentType(MediaType.APPLICATION_JSON).content(asJsonString(lista)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(1));
	}

	@Test
	void actualizarLista_ActualizacionFallida_DebeRetornarHttpStatusInternalServerError() throws Exception {
		doThrow(ActualizacionListaException.class).when(servicioLista).actualizar(any());

		mockMvc.perform(put("/listas").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Lista())))
				.andExpect(status().isInternalServerError());
	}

	@Test
	void actualizarLista_ListaNoEncontrada_DebeRetornarHttpStatusInternalServerError() throws Exception {
		doThrow(ListaNotFoundException.class).when(servicioLista).actualizar(any());

		mockMvc.perform(put("/listas").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Lista())))
				.andExpect(status().isInternalServerError());
	}

	@Test
	void eliminarLista_EliminacionExitosa_DebeRetornarHttpStatusNoContent() throws Exception {
		mockMvc.perform(delete("/listas/{id}", 1L)).andExpect(status().isNoContent());
	}

	@Test
	void eliminarLista_EliminacionFallida_DebeRetornarHttpStatusInternalServerError() throws Exception {
		doThrow(EliminacionListaException.class).when(servicioLista).eliminarPorId(anyLong());

		mockMvc.perform(delete("/listas/{id}", 1L)).andExpect(status().isInternalServerError());
	}

	@Test
	void eliminarLista_ListaNoEncontrada_DebeRetornarHttpStatusInternalServerError() throws Exception {
		doThrow(ListaNotFoundException.class).when(servicioLista).eliminarPorId(anyLong());

		mockMvc.perform(delete("/listas/{id}", 1L)).andExpect(status().isInternalServerError());
	}

	// Método utilitario para convertir un objeto a JSON
	private String asJsonString(Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
