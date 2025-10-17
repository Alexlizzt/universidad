package com.alexlizzt.universidad.servicios.contratos;

import java.util.List;
import java.util.Set;

import com.alexlizzt.universidad.modelo.entidades.Carrera;


public interface CarreraDAO extends GenericDAO<Carrera>{


    Iterable<Carrera> findCarrerasByNombreContains(String nombre);
    Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
    Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
    Set<Carrera> findAllById(List<Integer> listaIds);
    Iterable<Carrera> findCarrerasByProfesorNombreApellido(String nombre, String apellido);
}
