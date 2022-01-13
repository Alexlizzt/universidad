package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Empleado;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.EmpleadoDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.PersonaDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.ProfesorDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.EmpleadoMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PersonaDAO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/empleados")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
@Api(value = "Aplicaciones relacionadas con los empleados", tags = "Acciones sobre Empleados")
public class EmpleadoDtoController extends PersonaDtoController{

    public EmpleadoDtoController(@Qualifier("empleadoDAOImpl") PersonaDAO service, EmpleadoMapper empleadoMapper) {
        super(service, "Empleado", empleadoMapper);
    }

    @GetMapping
    @ApiOperation(value = "Consultar todos los empleados")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ejecutado satisfactoriamente")
    })
    public ResponseEntity<?> obtenerEmpleados() {
        Map<String, Object> mensaje = new HashMap<>();
        Stream<Persona> personas = ((List<Persona>) super.obtenerTodos()).stream();
        List<Persona> empleados = personas.filter(persona -> persona instanceof Empleado).collect(Collectors.toList());

        if(empleados.isEmpty()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No se encontrarn los %ss cargadas", nombre_entidad));
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<EmpleadoDTO> empleadoDTOS = empleadoMapper.mapEmpleado(empleados);

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", empleadoDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PostMapping
    @ApiOperation(value = "Agregar empleado")
    public ResponseEntity<?> agregarEmpleado(@RequestBody @ApiParam(name = "Empleado de la universidad") PersonaDTO personaDTO, BindingResult result) {
        Map<String, Object> mensaje = new HashMap<>();
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        PersonaDTO save = super.agregarPersona(empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO));
        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", save);

        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar empleado por codigo")
    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable @ApiParam(name = "Codigo del sistema") Integer id) {
        Map<String, Object> mensaje = new HashMap<>();
        PersonaDTO dto = super.buscarPersonaPorId(id);

        if (dto == null){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("mensaje", String.format("No existe %s con ID %d", nombre_entidad, id));
            return ResponseEntity.badRequest().body(mensaje);
        }

        mensaje.put("success", Boolean.TRUE);
        mensaje.put("data", empleadoMapper.mapEmpleado((EmpleadoDTO) dto));

        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualizar los datos del empleado")
    public ResponseEntity<?> actualizarProfesor(@PathVariable Integer id, @RequestBody @ApiParam(name = "Empleado de la universidad") EmpleadoDTO empleadoDTO, BindingResult result){
        Map<String, Object> mensaje = new HashMap<>();
        Empleado empleadoUpdate = null;
        PersonaDTO personaDTO = super.buscarPersonaPorId(id);
        if(result.hasErrors()){
            mensaje.put("success", Boolean.FALSE);
            mensaje.put("validaciones", super.obtenerValidaciones(result));
            return ResponseEntity.badRequest().body(mensaje);
        }

        Empleado empleado = empleadoMapper.mapEmpleado(empleadoDTO);

        empleadoUpdate = empleadoMapper.mapEmpleado((EmpleadoDTO) personaDTO);
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellido(empleado.getApellido());
        empleadoUpdate.setDni(empleado.getDni());
        empleadoUpdate.setDireccion(empleado.getDireccion());
        empleadoUpdate.setSueldo(empleado.getSueldo());
        empleadoUpdate.setTipoEmpleado(empleado.getTipoEmpleado());

        mensaje.put("datos", service.save(empleadoUpdate));
        mensaje.put("success", Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensaje);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Eliminar empleado del sistema")
    public ResponseEntity<?> borrarProfesor(@PathVariable Integer id){
        service.deteteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
