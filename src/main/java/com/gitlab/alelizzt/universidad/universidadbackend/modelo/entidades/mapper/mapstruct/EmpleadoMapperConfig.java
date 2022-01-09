package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Empleado;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.EmpleadoDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface EmpleadoMapperConfig {

    @InheritConfiguration(name = "mapPersona")
    void mapEmpleado(Empleado empleado, @MappingTarget EmpleadoDTO empleadoDTO);
}
