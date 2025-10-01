package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Empleado;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.TipoEmpleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("repositorioEmpleados")
public interface EmpleadoRepository extends PersonaRepository{
    @Query("SELECT e FROM Empleado e WHERE e.tipoEmpleado = ?1")
    Iterable<Empleado> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);

}
