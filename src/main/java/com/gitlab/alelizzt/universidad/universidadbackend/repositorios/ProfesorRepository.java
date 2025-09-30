package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioProfesores")
public interface ProfesorRepository extends EmpleadoRepository {
    @Query("SELECT p FROM Profesor p JOIN FETCH p.carreras c WHERE c.nombre = ?1")
    Iterable<Profesor> findProfesoresByCarrera(String nombreCarrera);
}
