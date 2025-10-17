package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.dto.AlumnoDTO;
import com.alexlizzt.universidad.modelo.entidades.dto.PersonaDTO;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.AlumnoMapper;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;
import com.alexlizzt.universidad.utils.JsonKeys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/alumnos")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Tag(name = "Alumnos", description = "Operaciones relacionadas con los alumnos")
public class AlumnoDtoController extends PersonaDtoController {

    public AlumnoDtoController(@Qualifier("alumnoDAOImpl") PersonaDAO service, AlumnoMapper alumnoMapper) {
        super(service, "Alumno", alumnoMapper);
    }

    @GetMapping
    @Operation(summary = "Consultar todos los alumnos", description = "Devuelve todos los alumnos registrados")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    public ResponseEntity<Map<String, Object>> obtenerAlumnos() {
        Map<String, Object> mensaje = new HashMap<>();
        Stream<Persona> personas = StreamSupport.stream(super.obtenerTodos().spliterator(), false);

        List<Persona> alumnos = personas
                .filter(Alumno.class::isInstance)
                .toList();

        if (alumnos.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontraron los %ss cargados", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<AlumnoDTO> alumnoDTOS = alumnoMapper.mapAlumno(alumnos);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", alumnoDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @Operation(summary = "Agregar un nuevo alumno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alumno creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
    })
    public ResponseEntity<Map<String, Object>> agregarAlumno(
            @Valid @RequestBody
            @Parameter(description = "Alumno a registrar") PersonaDTO personaDTO,
            BindingResult result) {

        Map<String, Object> mensaje = new HashMap<>();

        if (result.hasErrors()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.VALIDATIONS, super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO save = super.agregarPersona(alumnoMapper.mapAlumno((AlumnoDTO) personaDTO));
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", save);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar un alumno por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alumno encontrado"),
            @ApiResponse(responseCode = "400", description = "Alumno no encontrado", content = @Content)
    })
    public ResponseEntity<Map<String, Object>> obtenerAlumnoPorId(
            @PathVariable
            @Parameter(description = "ID del alumno a consultar") Integer id) {

        Map<String, Object> mensaje = new HashMap<>();
        PersonaDTO dto = super.buscarPersonaPorId(id);

        if (dto == null) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No existe %s con ID %d", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        mensaje.put(JsonKeys.DATA, alumnoMapper.mapAlumno((AlumnoDTO) dto));
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de un alumno existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Alumno actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o alumno no encontrado", content = @Content)
    })
    public ResponseEntity<Map<String, Object>> actualizarAlumno(
            @PathVariable
            @Parameter(description = "ID del alumno a actualizar") Integer id,
            @Valid @RequestBody
            @Parameter(description = "Datos actualizados del alumno") AlumnoDTO alumnoDTO,
            BindingResult result) {

        Map<String, Object> mensaje = new HashMap<>();

        if (result.hasErrors()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.VALIDATIONS, super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO personaDTO = super.buscarPersonaPorId(id);
        if (personaDTO == null) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No existe %s con ID %d", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Alumno alumno = alumnoMapper.mapAlumno(alumnoDTO);
        Alumno alumnoUpdate = alumnoMapper.mapAlumno((AlumnoDTO) personaDTO);

        alumnoUpdate.setNombre(alumno.getNombre());
        alumnoUpdate.setApellido(alumno.getApellido());
        alumnoUpdate.setDni(alumno.getDni());
        alumnoUpdate.setDireccion(alumno.getDireccion());

        mensaje.put(JsonKeys.DATA, service.save(alumnoUpdate));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un alumno por ID")
    @ApiResponse(responseCode = "202", description = "Alumno eliminado exitosamente")
    public ResponseEntity<Void> borrarAlumno(
            @PathVariable
            @Parameter(description = "ID del alumno a eliminar") Integer id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}