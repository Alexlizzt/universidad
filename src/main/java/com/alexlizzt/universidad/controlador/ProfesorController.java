package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.controlador.dto.ProfesorDtoController;
import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;
import com.alexlizzt.universidad.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @deprecated Desde la versión 2.0. Usa {@link ProfesorDtoController} en su
 *             lugar.
 */
@Deprecated(since = "2.0", forRemoval = true)
@RestController
@RequestMapping("/profesores")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class ProfesorController extends PersonaController {

    private final ProfesorDAO profesorDAO;

    public ProfesorController(@Qualifier("profesorDAOImpl") PersonaDAO service, ProfesorDAO profesorDAO) {
        super(service);
        this.profesorDAO = profesorDAO;
        nombreEntidad = "Profesor";
    }

    /**
     * @deprecated Usa
     *             {@link ProfesorDtoController#buscarProfesoresPorCarrera(String)}
     *             en su lugar.
     */
    @Deprecated
    @GetMapping("/carrera")
    public ResponseEntity<?> buscarProfesoresPorCarrera(@RequestParam String carrera) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Profesor> profesoresByCarrera = (List<Profesor>) profesorDAO.findProfesoresByCarrera(carrera);
        if (profesoresByCarrera.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se ha encontrado algun profesor en la carrera %s", carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", profesoresByCarrera);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Usa
     *             {@link ProfesorDtoController#asignarCarrerasProfesor(Integer, Set)}
     *             en su lugar.
     */
    @Deprecated
    @PutMapping("{idProfesor}/carrera/{idCarreras}")
    public ResponseEntity<?> asignarCarrerasProfesor(@PathVariable Integer idProfesor,
            @PathVariable Set<Carrera> idCarreras) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oProfesor = service.findById(idProfesor);
        if (!oProfesor.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El/La profesor/a con id %d no existe", idProfesor));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Profesor profesor = (Profesor) oProfesor.get();
        profesor.setCarreras(idCarreras);

        mensaje.put("datos", service.save(profesor));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }

}
