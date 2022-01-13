package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Profesor;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.ProfesorDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", config = ProfesorMapperConfig.class)
public abstract class ProfesorMapper {

    public abstract ProfesorDTO mapProfesor(Profesor profesor);
    public abstract Profesor mapProfesor(ProfesorDTO profesorDTO);
    public abstract List<ProfesorDTO> mapProfesor(List<Persona> profesores);
}
