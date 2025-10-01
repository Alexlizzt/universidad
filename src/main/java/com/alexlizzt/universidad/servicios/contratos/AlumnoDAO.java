package com.alexlizzt.universidad.servicios.contratos;

import com.alexlizzt.universidad.modelo.entidades.Alumno;

public interface AlumnoDAO extends PersonaDAO{
    Iterable<Alumno> buscarAlumnosPorNombreCarrera(String nombre);
}
