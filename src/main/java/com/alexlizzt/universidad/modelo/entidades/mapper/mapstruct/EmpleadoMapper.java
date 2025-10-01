package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Empleado;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.dto.EmpleadoDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", config = EmpleadoMapperConfig.class)
public abstract class EmpleadoMapper {

    public abstract EmpleadoDTO mapEmpleado(Empleado empleado);
    public abstract Empleado mapEmpleado(EmpleadoDTO empleadoDTO);
    public abstract List<EmpleadoDTO> mapEmpleado(List<Persona> empleado);
}
