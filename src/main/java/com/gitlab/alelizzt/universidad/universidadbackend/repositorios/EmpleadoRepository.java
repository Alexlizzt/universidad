package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioEmpleados")
public interface EmpleadoRepository extends PersonaRepository{
    @Query("select e from Empleado e where ?1 IN(e.tipoEmpleado)")
    Iterable<Persona> findEmpleadoByTipoEmpleado(String tipoEmpleado);
}
