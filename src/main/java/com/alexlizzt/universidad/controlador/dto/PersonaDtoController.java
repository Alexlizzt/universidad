package com.alexlizzt.universidad.controlador.dto;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.Empleado;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.modelo.entidades.dto.PersonaDTO;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.AlumnoMapper;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.EmpleadoMapper;
import com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct.ProfesorMapper;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PersonaDtoController extends GenericDtoController<Persona, PersonaDAO> {

    protected AlumnoMapper alumnoMapper;
    protected ProfesorMapper profesorMapper;
    protected EmpleadoMapper empleadoMapper;

    public PersonaDtoController(PersonaDAO service, String nombreEntidad, AlumnoMapper alumnoMapper) {
        super(service, nombreEntidad);
        this.alumnoMapper = alumnoMapper;
    }

    public PersonaDtoController(PersonaDAO service, String nombreEntidad, ProfesorMapper profesorMapper) {
        super(service, nombreEntidad);
        this.profesorMapper = profesorMapper;
    }

    public PersonaDtoController(PersonaDAO service, String nombreEntidad, EmpleadoMapper empleadoMapper) {
        super(service, nombreEntidad);
        this.empleadoMapper = empleadoMapper;
    }

    public List<PersonaDTO> obtenerPersonas(){
        List<Persona> personas = super.obtenerTodos();
        if (personas.isEmpty()) {
        return Collections.emptyList();
    }

    List<PersonaDTO> dtos = new ArrayList<>();

    for (Persona persona : personas) {
        if (persona instanceof Alumno alumno) {
            dtos.add(alumnoMapper.mapAlumno(alumno));
        } else if (persona instanceof Profesor profesor) {
            dtos.add(profesorMapper.mapProfesor(profesor));
        } else if (persona instanceof Empleado empleado) {
            dtos.add(empleadoMapper.mapEmpleado(empleado));
        }
    }
        return dtos;
    }

    public PersonaDTO agregarPersona(Persona persona) {
        Persona personaEntidad = super.agregarEntidad(persona);

        if (personaEntidad instanceof Alumno alumno) {
            return alumnoMapper.mapAlumno(alumno);
        } else if (personaEntidad instanceof Profesor profesor) {
            return profesorMapper.mapProfesor(profesor);
        } else if (personaEntidad instanceof Empleado empleado) {
            return empleadoMapper.mapEmpleado(empleado);
        }

        return null; // o lanza una excepción si es un tipo desconocido
    }

    public PersonaDTO buscarPersonaPorId(Integer id) {
        Optional<Persona> oPersona = service.findById(id);
        if (oPersona.isEmpty()) {
            return null;
        }

        Persona persona = oPersona.get();

        if (persona instanceof Alumno alumno) {
            return alumnoMapper.mapAlumno(alumno);
        } else if (persona instanceof Profesor profesor) {
            return profesorMapper.mapProfesor(profesor);
        } else if (persona instanceof Empleado empleado) {
            return empleadoMapper.mapEmpleado(empleado);
        }

        return null; // o lanza una excepción si no es un tipo esperado
    }

}
