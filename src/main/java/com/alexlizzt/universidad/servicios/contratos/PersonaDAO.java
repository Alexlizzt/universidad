package com.alexlizzt.universidad.servicios.contratos;

import com.alexlizzt.universidad.modelo.entidades.Persona;

import java.util.Optional;

public interface PersonaDAO extends GenericDAO<Persona>{

    Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);
    Optional<Persona> buscarPorDni(String dni);
    Iterable<Persona> buscarPersonasPorApellido(String apellido);
}
