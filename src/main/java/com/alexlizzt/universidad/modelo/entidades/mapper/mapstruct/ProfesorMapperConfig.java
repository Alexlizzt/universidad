package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.modelo.entidades.dto.ProfesorDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface ProfesorMapperConfig extends PersonaMapperConfig{
    @InheritConfiguration(name = "mapPersona")
    void mapProfesor(Profesor profesor, @MappingTarget ProfesorDTO profesorDTO);
}
