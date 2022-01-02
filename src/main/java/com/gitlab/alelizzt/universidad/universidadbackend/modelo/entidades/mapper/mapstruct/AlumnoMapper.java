package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Alumno;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.AlumnoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = AlumnoMapperConfig.class)
public abstract class AlumnoMapper {

    public abstract AlumnoDTO mapAlumno(Alumno alumno);
    public abstract Alumno mapAlumno(AlumnoDTO alumnoDTO);

}
