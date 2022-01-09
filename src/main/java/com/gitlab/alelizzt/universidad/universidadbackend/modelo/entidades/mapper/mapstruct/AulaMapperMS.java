package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Aula;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.AulaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

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
}
