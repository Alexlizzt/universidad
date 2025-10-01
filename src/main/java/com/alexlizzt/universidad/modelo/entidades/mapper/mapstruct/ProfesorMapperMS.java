package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.modelo.entidades.dto.ProfesorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProfesorMapperMS {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "sueldo", target = "sueldo")
    })
    ProfesorDTO mapProfesor(Profesor profesor);
}
