package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.servicios.contratos.ReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/reports")
@Tag(name = "Reportes", description = "Generacion de reportes en pdf")
public class ReporteController {

    private ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/general")
    @Operation(summary = "Generar reporte general de la universidad", description = "Devuelve toda la informacion general de la universidad")
    @ApiResponse(responseCode = "200", description = "Reporte exitoso")
    public ResponseEntity<byte[]> generarReporteGeneral() {
        ByteArrayInputStream bis = reporteService.generarReporteGeneral();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporte-general.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }

    @GetMapping("/alumnos-por-carrera/{carrera}")
    @Operation(summary = "Generar reporte de alumnos por carrera", description = "Devuelve todos los nombres de los alumnos por carrera")
    @ApiResponse(responseCode = "200", description = "Reporte exitoso")

    public ResponseEntity<byte[]> generarReporteAlumnosPorCarrera(@PathVariable String carrera){
        ByteArrayInputStream bis = reporteService.generarReporteAlumnosPorCarrera(carrera);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","inline; filename=reporte-alumnos.pdf");

        return  ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }
}
