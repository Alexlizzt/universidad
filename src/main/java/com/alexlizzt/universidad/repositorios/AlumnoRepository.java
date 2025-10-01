package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioAlumnos")
public interface AlumnoRepository extends PersonaRepository{

    // Cuando la carga es Lazy, usar join fetch
    @Query("SELECT a FROM Alumno a JOIN FETCH a.carrera c WHERE c.nombre = ?1")
    Iterable<Alumno> buscarAlumnosPorNombreCarrera(String nombre);
}
