package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.controlador.dto.EmpleadoDtoController;
import com.alexlizzt.universidad.modelo.entidades.Empleado;
import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.TipoEmpleado;
import com.alexlizzt.universidad.servicios.contratos.EmpleadoDAO;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @deprecated Desde la versión 2.0. Usa {@link EmpleadoDtoController} en su
 *             lugar.
 */
@Deprecated(since = "2.0", forRemoval = false)
@RestController
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class EmpleadoController extends PersonaController {

    public EmpleadoController(@Qualifier("empleadoDAOImpl") PersonaDAO empleadoDAO) {
        super(empleadoDAO);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link EmpleadoDtoController#buscarEmpleadoPorTipoEmpleado(TipoEmpleado)}
     *             en su lugar.
     */
    @Deprecated
    @GetMapping("/tipoempleado")
    public ResponseEntity<?> buscarEmpleadoPorTipoEmpleado(@RequestParam TipoEmpleado tipoEmpleado) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Empleado> empleadoByTipoEmpleado = (List<Empleado>) ((EmpleadoDAO) service)
                .findEmpleadoByTipoEmpleado(tipoEmpleado);
        if (empleadoByTipoEmpleado.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se ha encontrado algun empleado de tipo %s", tipoEmpleado));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", empleadoByTipoEmpleado);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa
     *             {@link EmpleadoDtoController}
     *             en su lugar.
     */
    @Deprecated
    @PutMapping("{idEmpleado}/pabellon")
    public ResponseEntity<?> asignarPabellonEmpleado(@PathVariable Integer idEmpleado, @RequestBody Pabellon pabellon) {
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oEmpleado = service.findById(idEmpleado);
        if (!oEmpleado.isPresent()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El/La empleado/a con id %d no existe", idEmpleado));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Empleado empleado = (Empleado) oEmpleado.get();
        empleado.setPabellon(pabellon);

        mensaje.put("datos", service.save(empleado));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
