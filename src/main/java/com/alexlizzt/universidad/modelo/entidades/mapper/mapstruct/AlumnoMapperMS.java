package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.dto.AlumnoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { DireccionMapperMS.class })
public interface AlumnoMapperMS {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "carrera.nombre", target = "carreraNombre"),
    })
    AlumnoDTO toDTO(Alumno alumno);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "dni", target = "dni"),
            @Mapping(source = "direccion", target = "direccion")
    })
    Alumno toEntity(AlumnoDTO alumnoDTO);
}
