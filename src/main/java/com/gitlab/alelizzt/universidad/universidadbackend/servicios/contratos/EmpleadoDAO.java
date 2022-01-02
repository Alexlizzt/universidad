package com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;

public interface EmpleadoDAO extends PersonaDAO{
    Iterable<Persona> findEmpleadoByTipoEmpleado(String tipoEmpleado);
}
