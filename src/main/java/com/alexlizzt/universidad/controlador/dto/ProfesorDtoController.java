package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.modelo.entidades.dto.PersonaDTO;
import com.alexlizzt.universidad.modelo.entidades.dto.ProfesorDTO;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.ProfesorMapper;
import com.alexlizzt.universidad.servicios.contratos.CarreraDAO;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;
import com.alexlizzt.universidad.servicios.contratos.ProfesorDAO;
import com.alexlizzt.universidad.utils.JsonKeys;

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
import java.util.stream.Stream;

@RestController
@RequestMapping("/profesores")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Tag(name = "Profesores", description = "Aplicaciones relacionadas con los profesores")
public class ProfesorDtoController extends PersonaDtoController {

    private final CarreraDAO carreraService;

    public ProfesorDtoController(CarreraDAO carreraService, @Qualifier("profesorDAOImpl") PersonaDAO service,
            ProfesorMapper profesorMapper) {
        super(service, "Profesor", profesorMapper);
        this.carreraService = carreraService;
    }

    @GetMapping
    @Operation(summary = "Consultar todos los profesores")
    @ApiResponse(responseCode = "200", description = "Ejecutado satisfactoriamente")
    public ResponseEntity<Map<String, Object>> obtenerProfesores() {
        Map<String, Object> mensaje = new HashMap<>();
        Stream<Persona> personas = (super.obtenerTodos()).stream();

        List<Persona> profesores = personas
                .filter(Profesor.class::isInstance)
                .toList();

        if (profesores.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontrarn los %ses cargadas", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<ProfesorDTO> profesorDTOS = profesorMapper.mapProfesor(profesores);

        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        mensaje.put(JsonKeys.DATA, profesorDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @Operation(summary = "Agregar profesor")
    public ResponseEntity<Map<String, Object>> agregarProfesor(
            @Valid @RequestBody @Parameter(description = "Profesor de la universidad") PersonaDTO personaDTO,
            BindingResult result) {
        Map<String, Object> mensaje = new HashMap<>();
        if (result.hasErrors()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.VALIDATIONS, super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO save = super.agregarPersona(profesorMapper.mapProfesor((ProfesorDTO) personaDTO));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        mensaje.put(JsonKeys.DATA, save);

        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar profesor por codigo")
    public ResponseEntity<Map<String, Object>> obtenerProfesorPorId(
            @PathVariable @Parameter(name = "Codigo del sistema") Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        PersonaDTO dto = super.buscarPersonaPorId(id);

        if (dto == null) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No existe %s con ID %d", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        mensaje.put(JsonKeys.DATA, profesorMapper.mapProfesor((ProfesorDTO) dto));

        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos del profesor")
    public ResponseEntity<Map<String, Object>> actualizarProfesor(@PathVariable Integer id,
            @RequestBody @Parameter(description = "Profesor de la universidad") ProfesorDTO profesorDTO,
            BindingResult result) {
        Map<String, Object> mensaje = new HashMap<>();
        Profesor profesorUpdate = null;
        PersonaDTO personaDTO = super.buscarPersonaPorId(id);
        if (result.hasErrors()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.VALIDATIONS, super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Profesor profesor = profesorMapper.mapProfesor(profesorDTO);

        profesorUpdate = profesorMapper.mapProfesor((ProfesorDTO) personaDTO);
        profesorUpdate.setNombre(profesor.getNombre());
        profesorUpdate.setApellido(profesor.getApellido());
        profesorUpdate.setDni(profesor.getDni());
        profesorUpdate.setDireccion(profesor.getDireccion());
        profesorUpdate.setSueldo(profesor.getSueldo());

        mensaje.put(JsonKeys.DATA, service.save(profesorUpdate));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar profesor del sistema")
    public ResponseEntity<Void> borrarProfesor(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/carrera")
    @Operation(summary = "Buscar Profesores en base a la carrera")
    public ResponseEntity<Map<String, Object>> buscarProfesoresPorCarrera(
            @RequestParam @Parameter(description = "Carrera de la universidad") String carrera) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Profesor> profesoresByCarrera = (List<Profesor>) ((ProfesorDAO) service).findProfesoresByCarrera(carrera);
        if (profesoresByCarrera.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE,
                    String.format("No se ha encontrado algun profesor en la carrera %s", carrera));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put(JsonKeys.DATA, profesoresByCarrera);
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("{idProfesor}/carrera/{idCarreras}")
    public ResponseEntity<Map<String, Object>> asignarCarrerasProfesor(@PathVariable Integer idProfesor,
            @PathVariable List<Integer> idCarreras) {

        Set<Carrera> carreras = carreraService.findAllById(idCarreras);

        Optional<Persona> oProfesor = service.findById(idProfesor);
        if (!oProfesor.isPresent()) {
            Map<String, Object> mensaje = new HashMap<>();
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("El/La profesor/a con id %d no existe", idProfesor));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Profesor profesor = (Profesor) oProfesor.get();
        profesor.setCarreras(carreras);

        Map<String, Object> mensaje = new HashMap<>();
        mensaje.put(JsonKeys.DATA, service.save(profesor));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

}
