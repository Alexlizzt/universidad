package com.gitlab.alelizzt.universidad.universidadbackend.controlador;

import com.gitlab.alelizzt.universidad.universidadbackend.exception.BadRequestException;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.GenericDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenericController <E, S extends GenericDAO<E>>{

    protected final S service;
    protected String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos(){
        Map<String, Object> mensaje = new HashMap<>();

        List<E> listado = (List<E>) service.findAll();
        if(listado.isEmpty()){
            //throw new BadRequestException(String.format("No se han encontrado %ss", nombreEntidad));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("no existe %s", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", listado);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/{codigo}")
    public E obtenerPorId(@PathVariable(value = "codigo", required = false) Integer id){
        Optional<E> oEntidad = service.findById(id);
        if(!oEntidad.isPresent()){
            throw new BadRequestException(String.format("La %s con id %d no existe", nombreEntidad, id));
        }
        return oEntidad.get();
    }

    @PostMapping
    public E agregarEntidad(@RequestBody E entidad){
        return service.save(entidad);
    }

    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable Integer id){
        Optional<E> oEntidad = service.findById(id);
        if(!oEntidad.isPresent()){
            throw new BadRequestException(String.format("La %s con id %d no existe", nombreEntidad, id));
        }
        service.deteteById(id);
    }


}
