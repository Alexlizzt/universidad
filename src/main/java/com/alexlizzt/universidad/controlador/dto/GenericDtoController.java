package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.servicios.contratos.GenericDAO;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GenericDtoController<E, S extends GenericDAO<E>> {

    protected final S service;
    protected final String nombreEntidad;
    private static final Logger logger = LoggerFactory.getLogger(GenericDtoController.class);

    public List<E> obtenerTodos() {
        return (List<E>) service.findAll();
    }

    public E obtenerPorId(Integer id) {
        return service.findById(id)
                .orElseThrow(() -> {
                    logger.warn("No se encontró la entidad con ID: {}", id);
                    return new EntityNotFoundException("No se encontró la entidad con ID: " + id);
                });
    }

    public E agregarEntidad(E entidad) {
        return service.save(entidad);
    }

    protected Map<String, Object> obtenerValidaciones(BindingResult result) {
        Map<String, Object> validaciones = new HashMap<>();
        result.getFieldErrors()
                .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
        return validaciones;
    }
}
