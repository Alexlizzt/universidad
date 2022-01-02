package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioProfesores")
public interface ProfesorRepository extends EmpleadoRepository {
    @Query("select p from Profesor p join fetch p.carreras c where ?1 IN(c.nombre)")
    Iterable<Persona> findProfesoresByCarrera(String carrera);
}
