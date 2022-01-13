package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Alumno;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.AlumnoDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.PersonaDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.AlumnoMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import io.swagger.annotations.*;
import io.swagger.models.auth.In;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Aplicaciones relacionadas con los alumnos", tags = "Acciones sobre Alumnos")
public class AlumnoDtoController extends PersonaDtoController{


    public AlumnoDtoController(@Qualifier("alumnoDAOImpl") PersonaDAO service, AlumnoMapper alumnoMapper) {
        super(service, "Alumno", alumnoMapper);
    }

    @GetMapping
    @ApiOperation(value = "Consultar todos los alumnos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ejecutado satisfactoriamente")
    })
    public ResponseEntity<?> obtenerAlumnos(){
        Map<String, Object> mensaje = new HashMap<>();
        Stream<Persona> personas = ((List<Persona>) super.obtenerTodos()).stream();
        List<Persona> alumnos = personas.filter(persona -> persona instanceof Alumno).collect(Collectors.toList());

        if (alumnos.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontrarn los %ss cargadas", nombre_entidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<AlumnoDTO> alumnoDTOS = alumnoMapper.mapAlumno(alumnos);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", alumnoDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @ApiOperation(value = "Agregar alumno")
    public ResponseEntity<?> agregarAlumno(@Valid @RequestBody @ApiParam(name = "Alumno de la universidad") PersonaDTO personaDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO save = super.agregarPersona(alumnoMapper.mapAlumno((AlumnoDTO) personaDTO));

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", save);

        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar Alumno por codigo")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable @ApiParam(name = "Codigo del sistema") Integer id){
         Map<String, Object> mensaje = new HashMap<>();
         PersonaDTO dto = super.buscarPersonaPorId(id);

         if (dto == null){
             mensaje.put("success", Boolean.FALSE);
             mensaje.put("mensaje", String.format("No existe %s con ID %d", nombre_entidad, id));
             return ResponseEntity.badRequest().body(mensaje);
         }

         mensaje.put("success", Boolean.TRUE);
         mensaje.put("data", alumnoMapper.mapAlumno((AlumnoDTO) dto));

         return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar los datos del alumno")
    public ResponseEntity<?> actualizarAlumno(@PathVariable Integer id, @RequestBody @ApiParam(name = "Alumno de la universidad") AlumnoDTO alumnoDTO, BindingResult result) {
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Alumno alumnoUpdate = null;
        PersonaDTO personaDTO = super.buscarPersonaPorId(id);
        if (personaDTO == null){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con ID %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Alumno alumno = alumnoMapper.mapAlumno(alumnoDTO);

        alumnoUpdate = alumnoMapper.mapAlumno((AlumnoDTO) personaDTO);
        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDni(alumno.getDni());
        alumnoUpdate.setDireccion(alumno.getDireccion());

        mensaje.put("datos", service.save(alumnoUpdate));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar alumno del sistema")
    public ResponseEntity<?> borrarAlumno(@PathVariable Integer id){
        service.deteteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
