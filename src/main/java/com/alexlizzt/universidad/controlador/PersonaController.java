package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
public class PersonaController extends GenericController<Persona, PersonaDAO> {

    public PersonaController(PersonaDAO service) {
        super(service);
    }

    @GetMapping("/nombre-apellido")
    public ResponseEntity<?> buscarPersonaPorNombreyApellido(@RequestParam String nombre, @RequestParam String apellido){
        Map<String, Object> mensaje = new HashMap<>();

        Optional<Persona> oPersona = service.buscarPorNombreYApellido(nombre, apellido);
        if(!oPersona.isPresent()){
            //throw new BadRequestException(String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro Persona con nombre %s y apellido %s", nombre, apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("datos", oPersona.get());
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/apellido")
    public ResponseEntity<?> buscarPorApellido(@RequestParam String apellido){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> oPersona = (List<Persona>) service.buscarPersonasPorApellido(apellido);
        if(oPersona.isEmpty()){
            //throw new BadRequestException(String.format("No se encontraron Personas con apellido %s", apellido));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron Personas con apellido %s", apellido));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", oPersona);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/dni")
    public ResponseEntity<?> buscarPorDni(@RequestParam String dni){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oPersona = service.buscarPorDni(dni);
        if(!oPersona.isPresent()){
            //throw new BadRequestException(String.format("No se encontro Persona con dni %d", dni));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontro Persona con dni %d", dni));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", oPersona.get());
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
    
}
