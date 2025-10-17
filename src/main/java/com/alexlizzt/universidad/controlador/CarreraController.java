package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.controlador.dto.CarreraDtoController;
import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.servicios.contratos.CarreraDAO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @deprecated Desde la versión 2.0. Usa {@link CarreraDtoController} en su
 *             lugar.
 */
@Deprecated(since = "2.0", forRemoval = false)
@RestController
@RequestMapping("/carreras")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class CarreraController extends GenericController<Carrera, CarreraDAO> {

    public CarreraController(CarreraDAO service) {
        super(service);
        nombreEntidad = "Carrera";
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link CarreraDtoController#agregarEntidad(Carrera, BindingResult)}
     *             en su lugar.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @PostMapping
    public ResponseEntity<?> agregarEntidad(@Valid @RequestBody Carrera carrera, BindingResult result) {

        Map<String, Object> validaciones = new HashMap<>();
        Map<String, Object> mensaje = new HashMap<>();
        if (result.hasErrors()) {
            result.getFieldErrors()
                    .forEach(error -> validaciones.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(validaciones);
        }
        mensaje.put("datos", service.save(carrera));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link CarreraDtoController#actualizarCarrera(Integer, Carrera, BindingResult)}
     *             en su lugar.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @PutMapping("{id}")
    public ResponseEntity<?> actualizarCarrera(@PathVariable Integer id, @RequestBody Carrera carrera) {
        Map<String, Object> mensaje = new HashMap<>();
        Carrera carreraUpdate = null;
        Optional<Carrera> oCarrera = service.findById(id);
        if (!oCarrera.isPresent()) {
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

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link CarreraDtoController}
     *             en su lugar.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @GetMapping("/carrera/contains/")
    public ResponseEntity<?> buscarCarrerasQueContenganNombre(@RequestParam String nombre) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByNombreContains = (List<Carrera>) service.findCarrerasByNombreContains(nombre);
        if (carrerasByNombreContains.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen carreras con %s en su nombre", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", carrerasByNombreContains);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link CarreraDtoController}
     *             en su lugar.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @GetMapping("/carrera/contains/ignore/")
    public ResponseEntity<?> buscarCarrerasQueContenganNombreNoCase(@RequestParam String nombre) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByNombreContainsIgnoreCase = (List<Carrera>) service
                .findCarrerasByNombreContainsIgnoreCase(nombre);
        if (carrerasByNombreContainsIgnoreCase.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen carreras con %s en su nombre", nombre));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", carrerasByNombreContainsIgnoreCase);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link CarreraDtoController}
     *             en su lugar.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @GetMapping("/carrera/anios/")
    public ResponseEntity<?> buscarCarrerasConCantidadAnios(@RequestParam Integer cantAnios) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Carrera> carrerasByCantidadAniosAfter = (List<Carrera>) service
                .findCarrerasByCantidadAniosAfter(cantAnios);
        if (carrerasByCantidadAniosAfter.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existen carreras con %d cantidad de años", cantAnios));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", carrerasByCantidadAniosAfter);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

}
