package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.servicios.contratos.GenericDAO;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GenericDtoController <E, S extends GenericDAO<E>> {

    protected final S service;
    protected final String nombre_entidad;

    public List<E> obtenerTodos(){
        return (List<E>) service.findAll();
    }

    public E obtenerPorId(Integer id){
        return (E) service.findById(id);
    }

    public E agregarEntidad(E entidad){
        return service.save(entidad);
    }

    protected Map<String, Object> obtenerValidaciones(BindingResult result){
        Map<String, Object> validaciones = new HashMap<>();
        result.getFieldErrors()
                .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
        return validaciones;
    }
}
