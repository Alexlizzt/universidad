package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.servicios.contratos.CarreraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class CarreraController extends GenericController<Carrera, CarreraDAO>{

    @Autowired
    public CarreraController(CarreraDAO service) {
        super(service);
        nombreEntidad = "Carrera";
    }

    @PostMapping
    public ResponseEntity<?> agregarEntidad(@Valid @RequestBody Carrera carrera, BindingResult result){
        /*if(carrera.getCantidadAnios() < 0){
            throw new BadRequestException("El campo cantidad de años no puede ser negativo");
        }
        if(carrera.getCantidadMaterias() < 0){
            throw new BadRequestException("El campo cantidad de materias no puede ser negativo");
        }*/
        Map<String, Object> validaciones = new HashMap<>();
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }
        mensaje.put("datos", service.save(carrera));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> actualizarCarrera (@PathVariable Integer id, @RequestBody Carrera carrera){
        Map<String, Object> mensaje = new HashMap<>();
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if(!oCarrera.isPresent()){
            //throw new BadRequestException(String.format("La carrera con id %d no existe", id));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("%s con id %d no existe", nombreEntidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }
        carreraUpdate = oCarrera.get();
        carreraUpdate.setCantidadAnios(carrera.getCantidadAnios());
        carreraUpdate.setCantidadMaterias(carrera.getCantidadMaterias());

        mensaje.put("datos", service.save(carreraUpdate));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/carrera/contains/")
    public ResponseEntity<?> buscarCarrerasQueContenganNombre(@RequestParam String nombre) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.findCarrerasByNombreContains(nombre);
        if (carrerasByNombreContains.isEmpty()) {
            //throw new BadRequestException(String.format("No existen carreras con %s en su nombre", nombre));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen carreras con %s en su nombre", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", carrerasByNombreContains);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }


    @GetMapping("/carrera/contains/ignore/")
    public ResponseEntity<?> buscarCarrerasQueContenganNombreNoCase(@RequestParam String nombre) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByNombreContainsIgnoreCase = (List<Carrera>) service.findCarrerasByNombreContainsIgnoreCase(nombre);
        if (carrerasByNombreContainsIgnoreCase.isEmpty()) {
            //throw new BadRequestException(String.format("No existen carreras con %s en su nombre", nombre));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen carreras con %s en su nombre", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", carrerasByNombreContainsIgnoreCase);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @GetMapping("/carrera/anios/")
    public ResponseEntity<?> buscarCarrerasConCantidadAnios(@RequestParam Integer cantAnios) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByCantidadAniosAfter= (List<Carrera>) service.findCarrerasByCantidadAniosAfter(cantAnios);
        if (carrerasByCantidadAniosAfter.isEmpty()) {
            //throw new BadRequestException(String.format("No existen carreras con %s en su nombre", nombre));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen carreras con %d cantidad de años", cantAnios));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", carrerasByCantidadAniosAfter);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

}
