package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.repositorios.CarreraRepository;
import com.alexlizzt.universidad.servicios.contratos.CarreraDAO;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarreraDAOImpl extends GenericDAOImpl<Carrera, CarreraRepository> implements CarreraDAO {

    public CarreraDAOImpl(CarreraRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findCarrerasByNombreContains(String nombre) {
        return repository.findCarrerasByNombreContains(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre) {
        return repository.findCarrerasByNombreContainsIgnoreCase(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios) {
        return repository.findCarrerasByCantidadAniosAfter(cantidadAnios);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Carrera> findCarrerasByProfesorNombreApellido(String nombre, String apellido) {
        return repository.buscarCarrerasPorProfesorNombreYApellido(nombre, apellido);
    }

    @Override
    public Set<Carrera> findAllById(List<Integer> listaIds) {
        return repository.findAllByIdIn(listaIds);
    }
}
