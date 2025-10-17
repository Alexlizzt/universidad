package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.dto.CarreraDTO;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.CarreraMapperMS;
import com.alexlizzt.universidad.servicios.contratos.CarreraDAO;
import com.alexlizzt.universidad.utils.JsonKeys;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Tag(name = "Carreras", description = "Aplicaciones relacionadas con las carreras")
public class CarreraDtoController extends GenericDtoController<Carrera, CarreraDAO> {

    private final CarreraMapperMS carreraMapper;

    public CarreraDtoController(CarreraDAO service, CarreraMapperMS carreraMapper) {
        super(service, "Carrera");
        this.carreraMapper = carreraMapper;
    }

    @GetMapping
    @Operation(summary = "Consultar todas las carreras")
    @ApiResponse(responseCode = "200", description = "Ejecutado satisfactoriamente")
    public ResponseEntity<Map<String, Object>> obtenerCarreras() {
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findAll();

        if (carreras.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontrarn las %ss cargadas", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<CarreraDTO> carreraDTOS = carreraMapper.mapCarreras(carreras);

        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        mensaje.put(JsonKeys.DATA, carreraDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @Operation(summary = "Agregar Carrera")
    public ResponseEntity<Map<String, Object>> agregarCarrera(
            @Valid @RequestBody @Parameter(description = "Carrera de la universidad") CarreraDTO carreraDTO,
            BindingResult result) {
        Map<String, Object> mensaje = new HashMap<>();
        if (result.hasErrors()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.VALIDATIONS, super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put(JsonKeys.DATA, super.agregarEntidad(carreraMapper.mapCarrera(carreraDTO)));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar Carrera por codigo")
    public ResponseEntity<Map<String, Object>> obtenerCarreraPorId(
            @PathVariable @Parameter(name = "Codigo del sistema") Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Carrera> oCarrera = service.findById(id);
        if (!oCarrera.isPresent()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("La carrera con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.DATA, carreraMapper.mapCarrera(oCarrera.get()));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de la Carrera")
    public ResponseEntity<Map<String, Object>> actualizarCarrera(@PathVariable Integer id,
            @RequestBody CarreraDTO carreraDTO) {
        Map<String, Object> mensaje = new HashMap<>();
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if (!oCarrera.isPresent()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("la carrera con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Carrera carrera = carreraMapper.mapCarrera(carreraDTO);

        carreraUpdate = oCarrera.get();
        carreraUpdate.setNombre(carrera.getNombre());
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidadMaterias(carrera.getCantidadMaterias());

        mensaje.put(JsonKeys.DATA, service.save(carreraUpdate));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar la carrera del sistema")
    public ResponseEntity<Void> borrarCarrera(
            @PathVariable @Parameter(description = "ID de la carrera a eliminar") Integer id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
