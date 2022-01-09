package com.gitlab.alelizzt.universidad.universidadbackend.controlador;

import com.gitlab.alelizzt.universidad.universidadbackend.exception.BadRequestException;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Empleado;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.EmpleadoDAO;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Deprecated
@RestController
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
public class EmpleadoController extends PersonaController{

    @Autowired
    public EmpleadoController(@Qualifier("empleadoDAOImpl") PersonaDAO empleadoDAO) {
        super(empleadoDAO);
    }

    /*@Override
    @GetMapping
    public List<Persona> obtenerTodos(){
        Stream<Persona> personas = ((List<Persona>) service.findAll()).stream();
        List<Persona> empleados = personas.filter(persona -> persona instanceof Empleado).collect(Collectors.toList());
        if(empleados.isEmpty()){
            throw new BadRequestException("No se ha encontrado algun empleado");
        }
        return empleados;
    }*/

    @GetMapping("/tipoempleado")
    public ResponseEntity<?> buscarEmpleadoPorTipoEmpleado(@RequestParam String tipoEmpleado){
        Map<String, Object> mensaje = new HashMap<>();
        List<Persona> empleadoByTipoEmpleado = (List<Persona>) ((EmpleadoDAO)service).findEmpleadoByTipoEmpleado(tipoEmpleado);
        if(empleadoByTipoEmpleado.isEmpty()){
            //throw new BadRequestException(String.format("No se ha encontrado algun empleado de tipo %s", tipoEmpleado));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se ha encontrado algun empleado de tipo %s", tipoEmpleado));
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("datos", empleadoByTipoEmpleado);
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("{idEmpleado}/pabellon")
    public ResponseEntity<?> asignarPabellonEmpleado(@RequestParam Integer idEmpleado, @RequestBody Pabellon pabellon){
        Map<String, Object> mensaje = new HashMap<>();
        Optional<Persona> oEmpleado = service.findById(idEmpleado);
        if(!oEmpleado.isPresent()){
            //throw new BadRequestException(String.format("El/La empleado/a con id %d no existe", idEmpleado));
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("El/La empleado/a con id %d no existe", idEmpleado));
            return ResponseEntity.badRequest().body(mensaje);
        }
        Empleado empleado = (Empleado)oEmpleado.get();
        empleado.setPabellon(pabellon);

        mensaje.put("datos", service.save(empleado));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.ok(mensaje);
    }
}
