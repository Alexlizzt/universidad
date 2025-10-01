package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.dto.AlumnoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", config = AlumnoMapperConfig.class)
public abstract class AlumnoMapper {

    public abstract AlumnoDTO mapAlumno(Alumno alumno);
    public abstract Alumno mapAlumno(AlumnoDTO alumnoDTO);
    public abstract List<AlumnoDTO> mapAlumno(List<Persona> alumnos);

}
