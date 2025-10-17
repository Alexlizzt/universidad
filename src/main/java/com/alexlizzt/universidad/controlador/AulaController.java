package com.alexlizzt.universidad.controlador;

import com.alexlizzt.universidad.controlador.dto.AulaDtoController;
import com.alexlizzt.universidad.modelo.entidades.Aula;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.Pizarron;
import com.alexlizzt.universidad.servicios.contratos.AulaDAO;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @deprecated Desde la versión 2.0. Usa {@link AulaDtoController} en su lugar.
 */
@Deprecated(since = "2.0", forRemoval = true)
@RestController
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class AulaController extends GenericController<Aula, AulaDAO> {
    public AulaController(AulaDAO service) {
        super(service);
        nombreEntidad = "Aula";
    }

    /**
     * @deprecated Desde la versión 2.0. Usa el endpoint correspondiente en
     *             {@link AulaDtoController#buscarAulaPorPabellon(String)} en su
     *             lugar.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @GetMapping("/pabellon")
    public ResponseEntity<?> buscarAulaPorPabellon(@RequestParam String pabellon) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.buscarAulasPorPabellon(pabellon);
        if (aulas.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron aulas en el pabellon %s", pabellon));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("datos", aulas);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa el endpoint correspondiente en
     *             {@link AulaDtoController#buscarAulaPorPizarron(Pizarron)} en su
     *             lugar.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @GetMapping("/pizarron")
    public ResponseEntity<?> buscarAulaPorPizarron(@RequestParam Pizarron tipoPizarron) {
        Map<String, Object> mensaje = new HashMap<>();
        List<Aula> aulas = (List<Aula>) service.buscarAulasPorPizarron(tipoPizarron);
        if (aulas.isEmpty()) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontraron aulas con pizarron %s", tipoPizarron.toString()));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", aulas);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    /**
     * @deprecated Desde la versión 2.0. Usa el endpoint correspondiente en
     *             {@link AulaDtoController#buscarAulaPorNumero(Integer)} en su
     *             lugar.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    @GetMapping("/num")
    public ResponseEntity<?> buscarAulaPorNumero(@RequestParam Integer num) {
        Map<String, Object> mensaje = new HashMap<>();
        Aula aula = service.buscarAulaporNumero(num);
        if (aula == null) {
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El aula con id %d no existe", num));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", aula);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
