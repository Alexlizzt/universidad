package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import com.alexlizzt.universidad.modelo.entidades.dto.PabellonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PabellonMapperMS {
    @Mappings({
            @Mapping(source = "id", target = "codigo"),
            @Mapping(source = "mts2", target = "mts2"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "direccion", target = "direccion")
    })
        PabellonDTO mapPabellon(Pabellon pabellon);
    List<PabellonDTO> mapPabellon(List<Pabellon> pabellones);
    @Mappings({
            @Mapping(source = "codigo", target = "id"),
            @Mapping(source = "mts2", target = "mts2"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "direccion", target = "direccion")
    })
    Pabellon mapPabellon(PabellonDTO pabellonDTO);
}
