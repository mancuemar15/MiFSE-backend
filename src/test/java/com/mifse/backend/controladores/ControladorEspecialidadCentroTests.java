package com.mifse.backend.controladores;

import com.mifse.backend.excepciones.EspecialidadCentroNotFoundException;
import com.mifse.backend.persistencia.modelos.Especialidad;
import com.mifse.backend.persistencia.modelos.EspecialidadCentro;
import com.mifse.backend.persistencia.modelos.TipoGuardiasFindesFestivos;
import com.mifse.backend.persistencia.modelos.TipoTrabajoFinResidencia;
import com.mifse.backend.servicios.ServicioEspecialidadCentro;
import com.mifse.backend.servicios.ServicioUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControladorEspecialidadCentro.class)
class ControladorEspecialidadCentroTests {

	private MockMvc mockMvc;

	@MockBean
	private ServicioEspecialidadCentro servicioEspecialidadCentro;

	@MockBean
	private ServicioUsuario servicioUsuario;

	@InjectMocks
	private ControladorEspecialidadCentro controladorEspecialidadCentro;

	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controladorEspecialidadCentro).build();
	}

	@Test
	void obtenerTodasEspecialidadesCentrosPorNombreTitulacion_DeberiaRetornarListaEspecialidadesCentros()
			throws Exception {
		String nombreTitulacion = "Medicina";
		List<EspecialidadCentro> especialidadesCentros = new ArrayList<>();
		Especialidad especialidad1 = new Especialidad();
		especialidad1.setId(1L);
		especialidad1.setNombre("Especialidad 1");
		Especialidad especialidad2 = new Especialidad();
		especialidad2.setId(2L);
		especialidad2.setNombre("Especialidad 2");

		TipoTrabajoFinResidencia tipoTrabajoFinResidencia = new TipoTrabajoFinResidencia();
		tipoTrabajoFinResidencia.setId(1L);

		TipoGuardiasFindesFestivos tipoGuardiasFindesFestivos = new TipoGuardiasFindesFestivos();
		tipoGuardiasFindesFestivos.setId(1L);

		EspecialidadCentro especialidadCentro1 = new EspecialidadCentro();
		especialidadCentro1.setEspecialidad(especialidad1);
		especialidadCentro1.setCentro(null);
		especialidadCentro1.setNecesidadCoche(false);
		especialidadCentro1.setTipoTrabajoFinResidencia(tipoTrabajoFinResidencia);
		especialidadCentro1.setHayClases(false);
		especialidadCentro1.setHayExamenes(false);
		especialidadCentro1.setNumeroDiasVacaciones(0);
		especialidadCentro1.setNumeroDiasLibreDisposicion(0);
		especialidadCentro1.setNumeroGuardiasMes(0);
		especialidadCentro1.setTipoGuardiasFindesFestivos(tipoGuardiasFindesFestivos);
		especialidadCentro1.setHayRotacionesExternas(false);
		especialidadCentro1.setSueldo(0);

		EspecialidadCentro especialidadCentro2 = new EspecialidadCentro();
		especialidadCentro2.setEspecialidad(especialidad2);
		especialidadCentro2.setCentro(null);
		especialidadCentro2.setNecesidadCoche(false);
		especialidadCentro2.setTipoTrabajoFinResidencia(tipoTrabajoFinResidencia);
		especialidadCentro2.setHayClases(false);
		especialidadCentro2.setHayExamenes(false);
		especialidadCentro2.setNumeroDiasVacaciones(0);
		especialidadCentro2.setNumeroDiasLibreDisposicion(0);
		especialidadCentro2.setNumeroGuardiasMes(0);
		especialidadCentro2.setTipoGuardiasFindesFestivos(tipoGuardiasFindesFestivos);
		especialidadCentro2.setHayRotacionesExternas(false);
		especialidadCentro2.setSueldo(0);

		especialidadesCentros.add(especialidadCentro1);
		especialidadesCentros.add(especialidadCentro2);

		when(servicioEspecialidadCentro.obtenerTodasPorNombreTitulacion(nombreTitulacion))
				.thenReturn(especialidadesCentros);

		mockMvc.perform(MockMvcRequestBuilders.get("/especialidades-centros/{nombreTitulacion}", nombreTitulacion)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$[0].especialidad.nombre").value("Especialidad 1"))
				.andExpect(jsonPath("$[1].especialidad.nombre").value("Especialidad 2"));
	}

	@Test
	void obtenerTodasEspecialidadesCentrosPorNombreTitulacion_EspecialidadTitulacionNotFoundException_DeberiaRetornarNotFound()
			throws Exception {
		String nombreTitulacion = "Medicina";

		when(servicioEspecialidadCentro.obtenerTodasPorNombreTitulacion(nombreTitulacion))
				.thenThrow(EspecialidadCentroNotFoundException.class);

		mockMvc.perform(get("/especialidades-centros/{nombreTitulacion}", nombreTitulacion))
				.andExpect(status().isNotFound()).andReturn();

		verify(servicioEspecialidadCentro).obtenerTodasPorNombreTitulacion(nombreTitulacion);
	}

}
