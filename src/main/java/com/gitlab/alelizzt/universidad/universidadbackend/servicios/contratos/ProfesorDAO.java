package com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;

public interface ProfesorDAO extends PersonaDAO{
    Iterable<Persona> findProfesoresByCarrera(String carrera);
}
