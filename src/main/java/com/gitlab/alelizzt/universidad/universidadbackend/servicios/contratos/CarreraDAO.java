package com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;


public interface CarreraDAO extends GenericDAO<Carrera>{


    Iterable<Carrera> findCarrerasByNombreContains(String nombre);
    Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
    Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
    //Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}
