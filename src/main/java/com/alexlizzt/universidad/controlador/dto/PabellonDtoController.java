package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import com.alexlizzt.universidad.modelo.entidades.dto.PabellonDTO;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.PabellonMapperMS;
import com.alexlizzt.universidad.servicios.contratos.PabellonDAO;
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

@RestController
@RequestMapping("/pabellones")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Tag(name = "Pabellones", description = "Aplicaciones relacionadas con los pabellones")
public class PabellonDtoController extends GenericDtoController<Pabellon, PabellonDAO>{

    @Autowired
    private PabellonMapperMS pabellonMapper;

    public PabellonDtoController(PabellonDAO service) {
        super(service, "Pabellon");
    }

    @GetMapping
    @Operation(summary = "Consultar todos los pabellones")
    @ApiResponse(responseCode = "200", description = "Ejecutado satisfactoriamente")
    public ResponseEntity<?> obtenerPabellones(){
        Map<String, Object> mensaje = new HashMap<>();
        List<Pabellon> pabellones = (List<Pabellon>) service.findAll();

        if(pabellones.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontrarn las %ss cargadas", nombre_entidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<PabellonDTO> pabellonDTOS = pabellonMapper.mapPabellon(pabellones);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", pabellonDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @Operation(summary = "Agregar Pabellon")
    public ResponseEntity<?> agregarPabellon(@Valid @RequestBody @Parameter(description = "Pabellon de la universidad") PabellonDTO pabellonDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", super.agregarEntidad(pabellonMapper.mapPabellon(pabellonDTO)));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar Pabellon por codigo")
    public ResponseEntity<?> obtenerPabellonPorId(@PathVariable @Parameter(description = "Codigo del Pabellon") Integer id){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Pabellon> oPabellon = service.findById(id);
        if(!oPabellon.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El Pabellon con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("datos", pabellonMapper.mapPabellon(oPabellon.get()));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos del Pabellon")
    public ResponseEntity<?> actualizarPabellon(@PathVariable Integer id, @RequestBody PabellonDTO pabellonDTO) {
        Map<String, Object> mensaje = new HashMap<>();
        Pabellon pabellonUpdate = null;
        Optional<Pabellon> oPabellon = service.findById(id);
        if(!oPabellon.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El Pabellon con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Pabellon pabellon = pabellonMapper.mapPabellon(pabellonDTO);

        pabellonUpdate = oPabellon.get();
        pabellonUpdate.setNombre(pabellon.getNombre());
        pabellonUpdate.setMts2(pabellon.getMts2());
        pabellonUpdate.setDireccion(pabellon.getDireccion());

        mensaje.put("datos", service.save(pabellonUpdate));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar el Pabellon del sistema")
    public ResponseEntity<?> borrarPabellon(@PathVariable Integer id){
        service.deteteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/localidad")
    @Operation(summary = "Consultar pabellon por localidad")
    public ResponseEntity<?> buscarPabellonesPorLocalidad(@RequestParam String localidad){
        Map<String, Object> mensaje = new HashMap<>();
        List<Pabellon> pabellones = (List<Pabellon>) service.buscarPabellonPorLocalidad(localidad);
        if(pabellones.isEmpty()){
            //throw new BadRequestException(String.format("No se encontraron Pabellones en la localidad %s", localidad));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron Pabellones en la localidad %s", localidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", pabellones);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/nombre")
    @Operation(summary = "Consultar pabellon por nombre")
    public ResponseEntity<?> buscarPabellonPorNombre(@RequestParam String nombre){
        Map<String, Object> mensaje = new HashMap<>();
        List<Pabellon> pabellones = (List<Pabellon>) service.buscarPabellonPorNombre(nombre);
        if(pabellones.isEmpty()){
            //throw new BadRequestException(String.format("No se encontraron Pabellones con nombre %s", nombre));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron Pabellones con nombre %s", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", pabellones);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
