package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Aula;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.AulaMapperMS;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class AulaDtoController extends GenericDtoController<Aula, AulaDAO> {

    @Autowired
    private AulaMapperMS mapper;
    public AulaDtoController(AulaDAO service) {
        super(service, "Aula");
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
