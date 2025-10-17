package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import com.alexlizzt.universidad.modelo.entidades.dto.PabellonDTO;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.PabellonMapperMS;
import com.alexlizzt.universidad.servicios.contratos.PabellonDAO;
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
@RequestMapping("/pabellones")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Tag(name = "Pabellones", description = "Aplicaciones relacionadas con los pabellones")
public class PabellonDtoController extends GenericDtoController<Pabellon, PabellonDAO> {

    private final PabellonMapperMS pabellonMapper;

    public PabellonDtoController(PabellonDAO service, PabellonMapperMS pabellonMapper) {
        super(service, "Pabellon");
        this.pabellonMapper = pabellonMapper;
    }

    @GetMapping
    @Operation(summary = "Consultar todos los pabellones")
    @ApiResponse(responseCode = "200", description = "Ejecutado satisfactoriamente")
    public ResponseEntity<Map<String, Object>> obtenerPabellones() {
        Map<String, Object> mensaje = new HashMap<>();
        List<Pabellon> pabellones = (List<Pabellon>) service.findAll();

        if (pabellones.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontrarn las %ss cargadas", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<PabellonDTO> pabellonDTOS = pabellonMapper.mapPabellon(pabellones);

        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        mensaje.put(JsonKeys.DATA, pabellonDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @Operation(summary = "Agregar Pabellon")
    public ResponseEntity<Map<String, Object>> agregarPabellon(
            @Valid @RequestBody @Parameter(description = "Pabellon de la universidad") PabellonDTO pabellonDTO,
            BindingResult result) {
        Map<String, Object> mensaje = new HashMap<>();
        if (result.hasErrors()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.VALIDATIONS, super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put(JsonKeys.DATA, super.agregarEntidad(pabellonMapper.mapPabellon(pabellonDTO)));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar Pabellon por codigo")
    public ResponseEntity<Map<String, Object>> obtenerPabellonPorId(
            @PathVariable @Parameter(description = "Codigo del Pabellon") Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Pabellon> oPabellon = service.findById(id);
        if (!oPabellon.isPresent()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("El Pabellon con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.DATA, pabellonMapper.mapPabellon(oPabellon.get()));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos del Pabellon")
    public ResponseEntity<Map<String, Object>> actualizarPabellon(@PathVariable Integer id,
            @RequestBody PabellonDTO pabellonDTO) {
        Map<String, Object> mensaje = new HashMap<>();
        Pabellon pabellonUpdate = null;
        Optional<Pabellon> oPabellon = service.findById(id);
        if (!oPabellon.isPresent()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("El Pabellon con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Pabellon pabellon = pabellonMapper.mapPabellon(pabellonDTO);

        pabellonUpdate = oPabellon.get();
        pabellonUpdate.setNombre(pabellon.getNombre());
        pabellonUpdate.setMts2(pabellon.getMts2());
        pabellonUpdate.setDireccion(pabellon.getDireccion());

        mensaje.put(JsonKeys.DATA, service.save(pabellonUpdate));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar el Pabellon del sistema")
    public ResponseEntity<Void> borrarPabellon(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/localidad")
    @Operation(summary = "Consultar pabellon por localidad")
    public ResponseEntity<Map<String, Object>> buscarPabellonesPorLocalidad(@RequestParam String localidad) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Pabellon> pabellones = (List<Pabellon>) service.buscarPabellonPorLocalidad(localidad);
        if (pabellones.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontraron Pabellones en la localidad %s", localidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put(JsonKeys.DATA, pabellones);
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/nombre")
    @Operation(summary = "Consultar pabellon por nombre")
    public ResponseEntity<Map<String, Object>> buscarPabellonPorNombre(@RequestParam String nombre) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Pabellon> pabellones = (List<Pabellon>) service.buscarPabellonPorNombre(nombre);
        if (pabellones.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontraron Pabellones con nombre %s", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put(JsonKeys.DATA, pabellones);
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
