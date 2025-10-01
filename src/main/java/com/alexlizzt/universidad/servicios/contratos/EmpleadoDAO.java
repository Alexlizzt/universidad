package com.alexlizzt.universidad.servicios.contratos;

import com.alexlizzt.universidad.modelo.entidades.Empleado;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.TipoEmpleado;

public interface EmpleadoDAO extends PersonaDAO{
    Iterable<Empleado> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
}
