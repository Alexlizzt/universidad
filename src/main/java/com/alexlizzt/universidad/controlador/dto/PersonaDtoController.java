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

import java.util.List;
import java.util.Optional;

public class PersonaDtoController extends GenericDtoController<Persona, PersonaDAO> {

    protected AlumnoMapper alumnoMapper;
    protected ProfesorMapper profesorMapper;
    protected EmpleadoMapper empleadoMapper;

    public PersonaDtoController(PersonaDAO service, String nombre_entidad, AlumnoMapper alumnoMapper) {
        super(service, nombre_entidad);
        this.alumnoMapper = alumnoMapper;
    }

    public PersonaDtoController(PersonaDAO service, String nombre_entidad, ProfesorMapper profesorMapper) {
        super(service, nombre_entidad);
        this.profesorMapper = profesorMapper;
    }

    public PersonaDtoController(PersonaDAO service, String nombre_entidad, EmpleadoMapper empleadoMapper) {
        super(service, nombre_entidad);
        this.empleadoMapper = empleadoMapper;
    }

    public PersonaDTO obtenerPersonas(){
        List<Persona> personas = super.obtenerTodos();
        PersonaDTO dto = null;
        if(personas.isEmpty()){
            return null;
        }
        if(personas instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) personas);
        } else if (personas instanceof Profesor){
            dto = profesorMapper.mapProfesor((Profesor) personas);
        } else if(personas instanceof Empleado) {
            dto = empleadoMapper.mapEmpleado((Empleado) personas);
        }
        return dto;
    }

    public PersonaDTO agregarPersona(Persona persona){
        Persona personaEntidad = super.agregarEntidad(persona);
        PersonaDTO dto = null;
        if(personaEntidad instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) personaEntidad);
        } else if (personaEntidad instanceof Profesor){
            dto = profesorMapper.mapProfesor((Profesor) personaEntidad);
        } else if(personaEntidad instanceof Empleado) {
            dto = empleadoMapper.mapEmpleado((Empleado) personaEntidad);
        }
        return dto;
    }

    public PersonaDTO buscarPersonaPorId(Integer id){
        Optional<Persona> oPersona = service.findById(id);
        Persona persona = null;
        PersonaDTO dto = null;
        if(oPersona.isEmpty()){
            return null;
        } else {
            persona = oPersona.get();
        }
        if (persona instanceof Alumno) {
            dto = alumnoMapper.mapAlumno((Alumno) persona);
        } else if (persona instanceof Profesor) {
            dto = profesorMapper.mapProfesor((Profesor) persona);
        } else if (persona instanceof Empleado) {
            dto = empleadoMapper.mapEmpleado((Empleado) persona);
        }
        return dto;
    }

}
