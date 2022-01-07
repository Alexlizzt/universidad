package com.gitlab.alelizzt.universidad.universidadbackend.controlador;

import com.gitlab.alelizzt.universidad.universidadbackend.exception.BadRequestException;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PabellonController extends GenericController<Pabellon, PabellonDAO>{

    public PabellonController(PabellonDAO service) {
        super(service);
        nombreEntidad="Pabellon";
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
