package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Empleado;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.EmpleadoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = EmpleadoMapperConfig.class)
public class EmpleadoMapper {

    public abstract EmpleadoDTO mapEmpleado(Empleado empleado);
    public abstract Empleado mapEmpleado(EmpleadoDTO empleadoDTO);
}
