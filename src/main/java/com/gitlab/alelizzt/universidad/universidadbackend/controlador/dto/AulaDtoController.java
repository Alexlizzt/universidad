package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Aula;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.AulaDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.AulaMapperMS;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.AulaDAO;
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

@RestController
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Aplicaciones relacionadas con las aulas", tags = "Acciones sobre Aulas")
public class AulaDtoController extends GenericDtoController<Aula, AulaDAO> {

    @Autowired
    private AulaMapperMS aulaMapper;
    public AulaDtoController(AulaDAO service) {
        super(service, "Aula");
    }

    @GetMapping
    @ApiOperation(value = "Consultar todas las carreras")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ejecutado satisfactoriamente")
    })
    public ResponseEntity<?> obtenerAulas(){
        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.findAll();

        if(aulas.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontrarn las %ss cargadas", nombre_entidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<AulaDTO> aulaDTOS = aulaMapper.mapAula(aulas);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", aulaDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @ApiOperation(value = "Agregar Aula")
    public ResponseEntity<?> agregarAula(@Valid @RequestBody @ApiParam(name = "Aula de la universidad") AulaDTO aulaDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", super.agregarEntidad(aulaMapper.mapAula(aulaDTO)));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar Aula por codigo")
    public ResponseEntity<?> obtenerAulaPorId(@PathVariable @ApiParam(name = "Codigo del aula") Integer id){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Aula> oAula = service.findById(id);
        if(!oAula.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El aula con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("datos", aulaMapper.mapAula(oAula.get()));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar los datos del Aula")
    public ResponseEntity<?> actualizarAula(@PathVariable Integer id, @RequestBody AulaDTO aulaDTO) {
        Map<String, Object> mensaje = new HashMap<>();
        Aula aulaUpdate = null;
        Optional<Aula> oAula = service.findById(id);
        if(!oAula.isPresent()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El aula con id %d no existe", id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Aula aula = aulaMapper.mapAula(aulaDTO);

        aulaUpdate = oAula.get();
        aulaUpdate.setNroAula(aula.getNroAula());
        aulaUpdate.setMedidas(aula.getMedidas());
        aulaUpdate.setCantidadPupitres(aula.getCantidadPupitres());
        aulaUpdate.setPizarron(aula.getPizarron());

        mensaje.put("datos", service.save(aulaUpdate));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar el aula del sistema")
    public ResponseEntity<?> borrarAula(@PathVariable Integer id){
        service.deteteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/pabellon")
    public ResponseEntity<?> buscarAulaPorPabellon(@RequestParam String pabellon){
        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.buscarAulasPorPabellon(pabellon);
        if(aulas.isEmpty()){
            //throw new BadRequestException(String.format("No se encontraron aulas en el pabellon %s", pabellon));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron aulas en el pabellon %s", pabellon));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("datos", aulas);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/pizarron")
    public ResponseEntity<?>buscarAulaPorPizarron(@RequestParam Pizarron tipoPizarron){
        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.buscarAulasPorPizarron(tipoPizarron);
        if(aulas.isEmpty()){
            //throw new BadRequestException(String.format("No se encontraron aulas con pizarron %s", tipoPizarron.toString()));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron aulas con pizarron %s", tipoPizarron.toString()));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", aulas);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/num")
    public ResponseEntity<?> buscarAulaPorNumero(@RequestParam Integer num){
        Map<String, Object> mensaje = new HashMap<>();
        Aula aula = service.buscarAulaporNumero(num);
        if (aula == null) {
            //throw new BadRequestException(String.format("El aula con id %d no existe", num));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El aula con id %d no existe", num));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos",aula);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
