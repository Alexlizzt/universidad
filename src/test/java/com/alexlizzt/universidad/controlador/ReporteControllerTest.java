package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.datos.DatosDummy;
import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.servicios.contratos.ReporteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteControllerTest {

    private ReporteController controller;
    private ReporteService reporteService;

    @BeforeEach
    void setUp() throws Exception {
        reporteService = mock(ReporteService.class);
        controller = new ReporteController(reporteService);

        // inyectar mock en el campo privado reporteService
        Field f = ReporteController.class.getDeclaredField("reporteService");
        f.setAccessible(true);
        f.set(controller, reporteService);
    }

    @Test
    void generarReporteGeneral(){
        byte[] expected = "PDF-GENERAL".getBytes();
        when(reporteService.generarReporteGeneral()).thenReturn(new ByteArrayInputStream(expected));

        ResponseEntity<byte[]> resp = controller.generarReporteGeneral();

        assertNotNull(resp);
        assertEquals(200, resp.getStatusCode().value());
        HttpHeaders headers = resp.getHeaders();
        assertTrue(headers.containsKey("Content-Disposition"));
        assertTrue(headers.getFirst("Content-Disposition").contains("reporte-general.pdf"));
        assertEquals(MediaType.APPLICATION_PDF, headers.getContentType());
        assertArrayEquals(expected, resp.getBody());

        verify(reporteService, times(1)).generarReporteGeneral();
    }

    @Test
    void generarReporteAlumnosPorCarrera() {
        // obtener nombre de carrera desde DatosDummy
        Carrera c = DatosDummy.carrera01(true);
        String carreraNombre = c.getNombre();

        byte[] expected = ("PDF-ALUMNOS-" + carreraNombre).getBytes();
        when(reporteService.generarReporteAlumnosPorCarrera(carreraNombre))
                .thenReturn(new ByteArrayInputStream(expected));

        ResponseEntity<byte[]> resp = controller.generarReporteAlumnosPorCarrera(carreraNombre);

        assertNotNull(resp);
        assertEquals(200, resp.getStatusCode().value());
        HttpHeaders headers = resp.getHeaders();
        assertTrue(headers.containsKey("Content-Disposition"));
        assertTrue(headers.getFirst("Content-Disposition").contains("reporte-alumnos.pdf"));
        assertEquals(MediaType.APPLICATION_PDF, headers.getContentType());
        assertArrayEquals(expected, resp.getBody());

        verify(reporteService, times(1)).generarReporteAlumnosPorCarrera(carreraNombre);
    }
}