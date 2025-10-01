package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.servicios.contratos.CarreraDAO;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Deprecated
@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class AlumnoController extends PersonaController{

    private final CarreraDAO carreraDAO;

    @Autowired
    public AlumnoController(@Qualifier("alumnoDAOImpl") PersonaDAO alumnoDAO, CarreraDAO carreraDAO) {
        super(alumnoDAO);
        nombreEntidad = "Alumno";
        this.carreraDAO = carreraDAO;
    }

    /*@Override
    @GetMapping
    public List<Persona> obtenerTodos(){
        Stream<Persona> personas = ((List<Persona>) service.findAll()).stream();
        List<Persona> alumnos = personas.filter(persona -> persona instanceof Alumno).collect(Collectors.toList());
        if(alumnos.isEmpty()){
            throw new BadRequestException("No se ha encontrado algun alumno");
        }
        return alumnos;
    }*/

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @RequestBody Persona alumno) {
        Map<String, Object> mensaje = new HashMap<>();
        Persona alumnoUpdate = null;
        Optional<Persona> oAlumno = service.findById(id);
        if (!oAlumno.isPresent()) {
            //throw new BadRequestException(String.format("El alumno con id %d no existe", id));
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

    @PutMapping("/{idAlumno}/carrera/{idCarrera}")
    public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer idAlumno, @PathVariable Integer idCarrera){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oAlumno = service.findById(idAlumno);
        if(!oAlumno.isPresent()){
            //throw new BadRequestException(String.format("El alumno con id %d no existe", idAlumno));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El alumno con id %d no existe", idAlumno));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Optional<Carrera> oCarrera = carreraDAO.findById(idCarrera);
        if(!oCarrera.isPresent()){
            //throw new BadRequestException(String.format("La carrera con id %d no existe", idCarrera));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("La carrera con id %d no existe", idCarrera));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Persona alumno = oAlumno.get();
        Carrera carrera = oCarrera.get();

        ((Alumno)alumno).setCarrera(carrera);

        mensaje.put("datos", service.save(alumno));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
