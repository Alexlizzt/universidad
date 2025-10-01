package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Direccion;
import com.alexlizzt.universidad.modelo.entidades.dto.DireccionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DireccionMapperMS {

    DireccionDTO toDTO(Direccion direccion);

    Direccion toEntity(DireccionDTO direccionDTO);
}
