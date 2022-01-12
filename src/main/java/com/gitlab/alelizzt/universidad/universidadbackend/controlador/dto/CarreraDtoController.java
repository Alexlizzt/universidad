package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.CarreraDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.CarreraMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.CarreraMapperMS;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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

@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Aplicaciones relacionadas con las carreras", tags = "Acciones sobre Carreras")
public class CarreraDtoController extends GenericDtoController<Carrera, CarreraDAO>{

    @Autowired
    private CarreraMapperMS carreraMapper;

    public CarreraDtoController(CarreraDAO service) {
        super(service, "Carrera");
    }

    @GetMapping
    @ApiOperation(value = "Consultar todas las carreras")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ejecutado satisfactoriamente")
    })
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
    @ApiOperation(value = "Agregar Carrera")
    public ResponseEntity<?> agregarCarrera(@Valid @RequestBody @ApiParam(name = "Carrera de la universidad") CarreraDTO carreraDTO, BindingResult result){
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
    @ApiOperation(value = "Consultar Carrera por codigo")
    public ResponseEntity<?> obtenerCarreraPorId(@PathVariable @ApiParam(name = "Codigo del sistema") Integer id){
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
    @ApiOperation(value = "Actualizar los datos de la Carrera")
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
    @ApiOperation(value = "Eliminar la carrera del sistema")
    public ResponseEntity<?> borrarCarrera(@PathVariable Integer id){
        service.deteteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
