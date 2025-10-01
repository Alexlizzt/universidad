package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.modelo.entidades.dto.ProfesorDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", config = ProfesorMapperConfig.class)
public abstract class ProfesorMapper {

    public abstract ProfesorDTO mapProfesor(Profesor profesor);
    public abstract Profesor mapProfesor(ProfesorDTO profesorDTO);
    public abstract List<ProfesorDTO> mapProfesor(List<Persona> profesores);
}
