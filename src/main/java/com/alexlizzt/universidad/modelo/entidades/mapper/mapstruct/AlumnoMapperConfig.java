package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.dto.AlumnoDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface AlumnoMapperConfig extends PersonaMapperConfig{

    @InheritConfiguration(name = "mapPersona")
    void mapAlumno(Alumno alumno, @MappingTarget AlumnoDTO alumnoDTO);
}
