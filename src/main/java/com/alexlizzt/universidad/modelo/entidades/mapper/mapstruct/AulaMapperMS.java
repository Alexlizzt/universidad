package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Aula;
import com.alexlizzt.universidad.modelo.entidades.dto.AulaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AulaMapperMS {

    @Mappings({
            @Mapping(source = "id", target = "codigo"),
            @Mapping(source = "nroAula", target = "numero_aula"),
            @Mapping(source = "medidas", target = "medidas"),
            @Mapping(source = "cantidadPupitres", target = "cantidad_pupitres"),
            @Mapping(source = "pizarron", target = "tipo_pizarron")
    })
    AulaDTO mapAula(Aula aula);
    List<AulaDTO> mapAula(List<Aula> aulas);
    @Mappings({
            @Mapping(source = "codigo", target = "id"),
            @Mapping(source = "numero_aula", target = "nroAula"),
            @Mapping(source = "medidas", target = "medidas"),
            @Mapping(source = "cantidad_pupitres", target = "cantidadPupitres"),
            @Mapping(source = "tipo_pizarron", target = "pizarron")
    })
    Aula mapAula(AulaDTO aulaDTO);
}
