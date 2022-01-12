package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.CarreraDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarreraMapperMS {

    @Mappings({
            @Mapping(source = "id", target = "codigo"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "cantidadMaterias", target = "cantidad_materias"),
            @Mapping(source = "cantidadAnios", target = "cantidad_anios")
    })
    CarreraDTO mapCarrera(Carrera carrera);
    List<CarreraDTO> mapCarreras(List<Carrera> carreras);
    @Mappings({
            @Mapping(source = "codigo", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "cantidad_materias", target = "cantidadMaterias"),
            @Mapping(source = "cantidad_anios", target = "cantidadAnios")
    })
    Carrera mapCarrera(CarreraDTO carreraDTO);
}
