package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.controlador.dto.GenericDtoController;
import com.alexlizzt.universidad.exception.BadRequestException;
import com.alexlizzt.universidad.servicios.contratos.GenericDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @deprecated Desde la versión 2.0. Usa {@link GenericDtoController} en su
 *             lugar.
 */
@Deprecated(since = "2.0", forRemoval = true)
public class GenericController<E, S extends GenericDAO<E>> {

    protected final S service;
    protected String nombreEntidad;

    public GenericController(S service) {
        this.service = service;
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link GenericDtoController#obtenerTodos()} en su lugar.
     */
    @Deprecated
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        Map<String, Object> mensaje = new HashMap<>();

        List<E> listado = (List<E>) service.findAll();
        if (listado.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("no existe %s", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", listado);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link GenericDtoController#obtenerPorId(Integer)} en su lugar.
     */
    @Deprecated
    @GetMapping("/{codigo}")
    public E obtenerPorId(@PathVariable(value = "codigo", required = false) Integer id) {
        Optional<E> oEntidad = service.findById(id);
        if (!oEntidad.isPresent()) {
            throw new BadRequestException(String.format("La %s con id %d no existe", nombreEntidad, id));
        }
        return oEntidad.get();
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link GenericDtoController#agregarEntidad(Object)} en su lugar.
     */
    @Deprecated
    @PostMapping
    public E agregarEntidad(@RequestBody E entidad) {
        return service.save(entidad);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link GenericDtoController#borrarPorId(Integer)} en su lugar.
     */
    @Deprecated
    @DeleteMapping("/{id}")
    public void borrarPorId(@PathVariable Integer id) {
        Optional<E> oEntidad = service.findById(id);
        if (!oEntidad.isPresent()) {
            throw new BadRequestException(String.format("La %s con id %d no existe", nombreEntidad, id));
        }
        service.deteteById(id);
    }

}
