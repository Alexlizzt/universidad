package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Alumno;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.AlumnoDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.PersonaDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.AlumnoMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class AlumnoDtoController {

    @Autowired
    @Qualifier("alumnoDAOImpl")
    private PersonaDAO personaDAO;
    @Autowired
    private AlumnoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Integer id){

         Map<String, Object> mensaje = new HashMap<>();
         Optional<Persona> oPersona = personaDAO.findById(id);
         PersonaDTO dto = mapper.mapAlumno((Alumno) oPersona.get());

         mensaje.put("success", Boolean.TRUE);
         mensaje.put("data", dto);

         return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    public ResponseEntity<?> agregarAlumno(@Valid @RequestBody PersonaDTO personaDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            Map<String, Object> validaciones = new HashMap<>();
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
            mensaje.put("validaciones", validaciones);
            return ResponseEntity.badRequest().body(mensaje);
        }

        Persona save = personaDAO.save(mapper.mapAlumno((AlumnoDTO) personaDTO));

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", mapper.mapAlumno((Alumno) save));

        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }


}
