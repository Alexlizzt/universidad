package com.gitlab.alelizzt.universidad.universidadbackend.controlador;

import com.gitlab.alelizzt.universidad.universidadbackend.exception.BadRequestException;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Empleado;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.EmpleadoDAO;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleados")
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
    public List<Persona> buscarEmpleadoPorTipoEmpleado(@RequestParam String tipoEmpleado){
        List<Persona> empleadoByTipoEmpleado = (List<Persona>) ((EmpleadoDAO)service).findEmpleadoByTipoEmpleado(tipoEmpleado);
        if(empleadoByTipoEmpleado.isEmpty()){
            throw new BadRequestException(String.format("No se ha encontrado algun empleado de tipo %s", tipoEmpleado));
        }
        return empleadoByTipoEmpleado;
    }

    @PutMapping("{idEmpleado}/pabellon")
    public Persona asignarPabellonEmpleado(@RequestParam Integer idEmpleado, @RequestBody Pabellon pabellon){
        Optional<Persona> oEmpleado = service.findById(idEmpleado);
        if(!oEmpleado.isPresent()){
            throw new BadRequestException(String.format("El/La empleado/a con id %d no existe", idEmpleado));
        }
        Empleado empleado = (Empleado)oEmpleado.get();
        empleado.setPabellon(pabellon);

        return service.save(empleado);
    }
}
