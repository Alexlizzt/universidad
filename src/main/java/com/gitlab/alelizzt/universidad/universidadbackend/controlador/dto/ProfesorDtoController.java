package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Profesor;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.AlumnoMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/profesores")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class ProfesorDtoController extends PersonaDtoController {

    private final ProfesorDAO profesorDAO;

    public ProfesorDtoController(@Qualifier("profesorDAOImpl") PersonaDAO service, AlumnoMapper alumnoMapper, ProfesorDAO profesorDAO) {
        super(service, "Profesor", alumnoMapper);
        this.profesorDAO = profesorDAO;
    }

    @GetMapping("/carrera")
    public ResponseEntity<?> buscarProfesoresPorCarrera(@RequestParam String carrera){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> profesoresByCarrera = (List<Persona>) profesorDAO.findProfesoresByCarrera(carrera);
        if(profesoresByCarrera.isEmpty()){
            //throw new BadRequestException("No se ha encontrado algun profesor");
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se ha encontrado algun profesor en la carrera %s", carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", profesoresByCarrera);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("{idProfesor}/carrera/{idCarreras}")
    public ResponseEntity<?> asignarCarrerasProfesor(@PathVariable Integer idProfesor, @PathVariable Set<Carrera> idCarreras){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oProfesor = service.findById(idProfesor);
        if(!oProfesor.isPresent()){
            //throw new BadRequestException(String.format("El/La profesor/a con id %d no existe", idProfesor));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El/La profesor/a con id %d no existe", idProfesor));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Profesor profesor= (Profesor) oProfesor.get();
        profesor.setCarrera(idCarreras);

        mensaje.put("datos", service.save(profesor));
        mensaje.put("success", Boolean.TRUE);

        return ResponseEntity.ok(mensaje);
    }
}
