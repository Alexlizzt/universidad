package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.PabellonMapperMS;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PabellonDAO;
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
@RequestMapping("/pabellones")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class PabellonDtoController extends GenericDtoController<Pabellon, PabellonDAO>{

    @Autowired
    private PabellonMapperMS mapper;

    public PabellonDtoController(PabellonDAO service) {
        super(service, "Pabellon");
    }

    @GetMapping("/localodad")
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
