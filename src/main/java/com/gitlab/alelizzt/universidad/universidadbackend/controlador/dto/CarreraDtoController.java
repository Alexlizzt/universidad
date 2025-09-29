package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.CarreraDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.CarreraMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.CarreraMapperMS;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Tag(name = "Carreras", description = "Aplicaciones relacionadas con las carreras")
public class CarreraDtoController extends GenericDtoController<Carrera, CarreraDAO>{

    @Autowired
    private CarreraMapperMS carreraMapper;

    public CarreraDtoController(CarreraDAO service) {
        super(service, "Carrera");
    }

    @GetMapping
    @Operation(summary = "Consultar todas las carreras")
    @ApiResponse(responseCode = "200", description = "Ejecutado satisfactoriamente")
    public ResponseEntity<?> obtenerCarreras(){
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carreras = (List<Carrera>) service.findAll();

        if(carreras.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontrarn las %ss cargadas", nombre_entidad));
            return ResponseEntity.badRequest().body(mensaje);
        }

        /*List<CarreraDTO> carreraDTOS = carreras
                .stream()
                .map(carreraMapper::mapCarrera)
                .collect(Collectors.toList());*/
        List<CarreraDTO> carreraDTOS = carreraMapper.mapCarreras(carreras);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", carreraDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @Operation(summary = "Agregar Carrera")
    public ResponseEntity<?> agregarCarrera(
            @Valid @RequestBody
            @Parameter(description = "Carrera de la universidad") CarreraDTO carreraDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", super.agregarEntidad(carreraMapper.mapCarrera(carreraDTO)));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar Carrera por codigo")
    public ResponseEntity<?> obtenerCarreraPorId(@PathVariable @Parameter(name = "Codigo del sistema") Integer id){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Carrera> oCarrera = service.findById(id);
        if(!oCarrera.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("La carrera con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("datos", carreraMapper.mapCarrera(oCarrera.get()));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de la Carrera")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer id, @RequestBody CarreraDTO carreraDTO){
        Map<String, Object> mensaje = new HashMap<>();
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if(!oCarrera.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("la carrera con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Carrera carrera = carreraMapper.mapCarrera(carreraDTO);

        carreraUpdate = oCarrera.get();
        carreraUpdate.setNombre(carrera.getNombre());
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidadMaterias(carrera.getCantidadMaterias());

        mensaje.put("datos", service.save(carreraUpdate));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar la carrera del sistema")
    public ResponseEntity<?> borrarCarrera(@PathVariable Integer id){
        service.deteteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
