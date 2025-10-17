package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.controlador.dto.AlumnoDtoController;
import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.servicios.contratos.CarreraDAO;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @deprecated Desde la versión 2.0. Usa {@link AlumnoDtoController} en su
 *             lugar.
 */
@Deprecated(since = "2.0", forRemoval = false)
@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class AlumnoController extends PersonaController {

    private final CarreraDAO carreraDAO;

    public AlumnoController(@Qualifier("alumnoDAOImpl") PersonaDAO alumnoDAO, CarreraDAO carreraDAO) {
        super(alumnoDAO);
        nombreEntidad = "Alumno";
        this.carreraDAO = carreraDAO;
    }

    /**
     * @deprecated Desde la versión 2.0. Use
     *             {@link AlumnoDtoController#actualizarAlumno}.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @RequestBody Persona alumno) {
        Map<String, Object> mensaje = new HashMap<>();
        Persona alumnoUpdate = null;
        Optional<Persona> oAlumno = service.findById(id);
        if (!oAlumno.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El alumno con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        alumnoUpdate = oAlumno.get();
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDni(alumno.getDni());
        alumnoUpdate.setDireccion(alumno.getDireccion());

        mensaje.put("datos", service.save(alumnoUpdate));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Desde la versión 2.0. Use el endpoint equivalente en
     *             {@link AlumnoDtoController}.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer idAlumno, @PathVariable Integer idCarrera) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oAlumno = service.findById(idAlumno);
        if (!oAlumno.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El alumno con id %d no existe", idAlumno));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if (!oCarrera.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("La carrera con id %d no existe", idCarrera));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Persona alumno = oAlumno.get();
        Carrera carrera = oCarrera.get();

        ((Alumno) alumno).setCarrera(carrera);

        mensaje.put("datos", service.save(alumno));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
