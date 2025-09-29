package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Profesor;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.PersonaDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.ProfesorDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.AlumnoMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.ProfesorMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/profesores")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Tag(name = "Profesores", description = "Aplicaciones relacionadas con los profesores")
public class ProfesorDtoController extends PersonaDtoController {

    public ProfesorDtoController(@Qualifier("profesorDAOImpl") PersonaDAO service, ProfesorMapper profesorMapper) {
        super(service, "Profesor", profesorMapper);
    }

    @GetMapping
    @Operation(summary = "Consultar todos los profesores")
    @ApiResponse(responseCode = "200", description = "Ejecutado satisfactoriamente")
    public ResponseEntity<?> obtenerProfesores(){
        Map<String, Object> mensaje = new HashMap<>();
        Stream<Persona> personas = ((List<Persona>) super.obtenerTodos()).stream();
        List<Persona> profesores = personas.filter(persona -> persona instanceof Profesor).collect(Collectors.toList());

        if (profesores.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontrarn los %ses cargadas", nombre_entidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<ProfesorDTO> profesorDTOS = profesorMapper.mapProfesor(profesores);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", profesorDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @Operation(summary = "Agregar profesor")
    public ResponseEntity<?> agregarProfesor(@Valid @RequestBody @Parameter(description = "Profesor de la universidad")PersonaDTO personaDTO, BindingResult result) {
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO save = super.agregarPersona(profesorMapper.mapProfesor((ProfesorDTO) personaDTO));
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", save);

        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar profesor por codigo")
    public ResponseEntity<?> obtenerProfesorPorId(@PathVariable @Parameter(name = "Codigo del sistema") Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        PersonaDTO dto = super.buscarPersonaPorId(id);

        if (dto == null){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con ID %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", profesorMapper.mapProfesor((ProfesorDTO) dto));

        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos del profesor")
    public ResponseEntity<?> actualizarProfesor(@PathVariable Integer id, @RequestBody @Parameter(description = "Profesor de la universidad") ProfesorDTO profesorDTO, BindingResult result) {
        Map<String, Object> mensaje = new HashMap<>();
        Profesor profesorUpdate = null;
        PersonaDTO personaDTO = super.buscarPersonaPorId(id);
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Profesor profesor = profesorMapper.mapProfesor(profesorDTO);

        profesorUpdate = profesorMapper.mapProfesor((ProfesorDTO) personaDTO);
        profesorUpdate.setNombre(profesor.getNombre());
        profesorUpdate.setApellido(profesor.getApellido());
        profesorUpdate.setDni(profesor.getDni());
        profesorUpdate.setDireccion(profesor.getDireccion());
        profesorUpdate.setSueldo(profesor.getSueldo());

        mensaje.put("datos", service.save(profesorUpdate));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar profesor del sistema")
    public ResponseEntity<?> borrarProfesor(@PathVariable Integer id){
        service.deteteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/carrera")
    @Operation(summary = "Buscar Profesores en base a la carrera")
    public ResponseEntity<?> buscarProfesoresPorCarrera(@RequestParam @Parameter(description = "Carrera de la universidad")String carrera){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> profesoresByCarrera = (List<Persona>)((ProfesorDAO)service).findProfesoresByCarrera(carrera);
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

    // TODO: Verificar funcionamiento
    @PutMapping("{idProfesor}/carrera/{idCarreras}")
    @Operation(summary = "Asignar Carreras al profesor")
    public ResponseEntity<?> asignarCarrerasProfesor(@PathVariable Integer idProfesor, @RequestBody Set<Carrera> idCarreras){
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
