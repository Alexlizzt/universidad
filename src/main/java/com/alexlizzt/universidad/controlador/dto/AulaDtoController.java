package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.modelo.entidades.Aula;
import com.alexlizzt.universidad.modelo.entidades.dto.AulaDTO;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.Pizarron;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.AulaMapperMS;
import com.alexlizzt.universidad.servicios.contratos.AulaDAO;

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
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Tag(name = "Aulas", description = "Operaciones relacionadas con las aulas")
public class AulaDtoController extends GenericDtoController<Aula, AulaDAO> {

    private final AulaMapperMS aulaMapper;

    public AulaDtoController(AulaDAO service, AulaMapperMS aulaMapper) {
        super(service, "Aula");
        this.aulaMapper = aulaMapper;
    }

    @GetMapping
    @Operation(summary = "Consultar todas las aulas")
    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
    public ResponseEntity<Map<String, Object>> obtenerAulas() {
        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.findAll();

        if (aulas.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontraron las %ss cargadas", nombreEntidad));
            return ResponseEntity.badRequest().body(mensaje);
        }

        List<AulaDTO> aulaDTOS = aulaMapper.mapAula(aulas);

        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        mensaje.put(JsonKeys.DATA, aulaDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @Operation(summary = "Agregar un aula nueva")
    public ResponseEntity<Map<String, Object>> agregarAula(
            @Valid @RequestBody
            @Parameter(description = "Datos del aula a registrar") AulaDTO aulaDTO,
            BindingResult result) {

        Map<String, Object> mensaje = new HashMap<>();

        if (result.hasErrors()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.VALIDATIONS, super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.DATA, super.agregarEntidad(aulaMapper.mapAula(aulaDTO)));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar aula por ID")
    public ResponseEntity<Map<String, Object>> obtenerAulaPorId(
            @PathVariable
            @Parameter(description = "ID del aula") Integer id) {

        Map<String, Object> mensaje = new HashMap<>();
        Optional<Aula> oAula = service.findById(id);

        if (oAula.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("El aula con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.DATA, aulaMapper.mapAula(oAula.get()));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos del aula")
    public ResponseEntity<Map<String, Object>> actualizarAula(
            @PathVariable
            @Parameter(description = "ID del aula a actualizar") Integer id,
            @Valid @RequestBody
            @Parameter(description = "Datos actualizados del aula") AulaDTO aulaDTO) {

        Map<String, Object> mensaje = new HashMap<>();
        Optional<Aula> oAula = service.findById(id);

        if (oAula.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("El aula con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Aula aula = aulaMapper.mapAula(aulaDTO);
        Aula aulaUpdate = oAula.get();

        aulaUpdate.setNroAula(aula.getNroAula());
        aulaUpdate.setMedidas(aula.getMedidas());
        aulaUpdate.setCantidadPupitres(aula.getCantidadPupitres());
        aulaUpdate.setPizarron(aula.getPizarron());

        mensaje.put(JsonKeys.DATA, service.save(aulaUpdate));
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar aula por ID")
    public ResponseEntity<Void> borrarAula(
            @PathVariable
            @Parameter(description = "ID del aula a eliminar") Integer id) {

        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/pabellon")
    @Operation(summary = "Buscar aulas por nombre de pabellón")
    public ResponseEntity<Map<String, Object>> buscarAulaPorPabellon(
            @RequestParam
            @Parameter(description = "Nombre del pabellón") String pabellon) {

        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.buscarAulasPorPabellon(pabellon);

        if (aulas.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontraron aulas en el pabellón %s", pabellon));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.DATA, aulas);
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/pizarron")
    @Operation(summary = "Buscar aulas por tipo de pizarrón")
    public ResponseEntity<Map<String, Object>> buscarAulaPorPizarron(
            @RequestParam
            @Parameter(description = "Tipo de pizarrón (e.g. PIZARRA_BLANCA, PIZARRA_TIZA)") Pizarron tipoPizarron) {

        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.buscarAulasPorPizarron(tipoPizarron);

        if (aulas.isEmpty()) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("No se encontraron aulas con pizarrón %s", tipoPizarron));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.DATA, aulas);
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/num")
    @Operation(summary = "Buscar aula por número")
    public ResponseEntity<Map<String, Object>> buscarAulaPorNumero(
            @RequestParam
            @Parameter(description = "Número del aula") Integer num) {

        Map<String, Object> mensaje = new HashMap<>();
        Aula aula = service.buscarAulaporNumero(num);

        if (aula == null) {
            mensaje.put(JsonKeys.SUCCESS, Boolean.FALSE);
            mensaje.put(JsonKeys.MESSAGE, String.format("El aula con número %d no existe", num));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put(JsonKeys.DATA, aula);
        mensaje.put(JsonKeys.SUCCESS, Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}