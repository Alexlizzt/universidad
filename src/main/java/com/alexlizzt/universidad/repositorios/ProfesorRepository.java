package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Profesor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioProfesores")
public interface ProfesorRepository extends EmpleadoRepository {
    @Query("SELECT p FROM Profesor p JOIN FETCH p.carreras c WHERE c.nombre = ?1")
    Iterable<Profesor> findProfesoresByCarrera(String nombreCarrera);
}
