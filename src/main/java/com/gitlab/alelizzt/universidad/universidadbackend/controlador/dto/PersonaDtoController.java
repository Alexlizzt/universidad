package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Alumno;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Empleado;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Profesor;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.PersonaDTO;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.AlumnoMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.EmpleadoMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.ProfesorMapper;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PersonaDAO;

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
}
