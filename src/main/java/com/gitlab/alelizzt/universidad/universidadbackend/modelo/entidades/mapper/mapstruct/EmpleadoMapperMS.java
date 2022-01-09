package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Empleado;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.EmpleadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmpleadoMapperMS {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "sueldo", target = "sueldo"),
            @Mapping(source = "tipoEmpleado", target = "tipo_empleado")
    })
    EmpleadoDTO mapEmpleado(Empleado empleado);
}
