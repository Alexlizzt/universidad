package com.alexlizzt.universidad.servicios.contratos;

import java.io.ByteArrayInputStream;

public interface ReporteService {
    ByteArrayInputStream generarReporteAlumnosPorCarrera(String carrera);
    ByteArrayInputStream generarReporteGeneral();
}
