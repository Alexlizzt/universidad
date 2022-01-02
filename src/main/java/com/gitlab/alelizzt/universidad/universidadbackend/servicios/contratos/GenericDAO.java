package com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;

import java.util.Optional;

public interface GenericDAO <E>{
    Optional<E> findById(Integer id);
    E save(E entidad);
    Iterable<E> findAll();
    void deteteById(Integer id);
}
