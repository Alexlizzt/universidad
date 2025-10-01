package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Empleado;
import com.alexlizzt.universidad.modelo.entidades.dto.EmpleadoDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface EmpleadoMapperConfig {

    @InheritConfiguration(name = "mapPersona")
    void mapEmpleado(Empleado empleado, @MappingTarget EmpleadoDTO empleadoDTO);
}
