package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface PersonaRepository extends CrudRepository<Persona, Integer> {

    @Query("SELECT p FROM Persona p WHERE p.nombre = ?1 AND p.apellido = ?2")
    Optional<Persona> buscarPorNombreYApellido(String nombre, String apellido);

    @Query("SELECT p FROM Persona p WHERE p.dni = ?1")
    Optional<Persona> buscarPorDni(String dni);

    @Query("SELECT p FROM Persona p WHERE p.apellido LIKE %?1%")
    Iterable<Persona> buscarPersonasPorApellido(String apellido);


}
