package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.modelo.entidades.dto.ProfesorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { DireccionMapperMS.class })
public interface ProfesorMapperMS {
    @Mappings({
            // Campos heredados de Persona - si los campos coinciden en nombre, no hace falta mapearlos explícitamente
            @Mapping(source = "sueldo", target = "sueldo")
    })
    ProfesorDTO mapProfesor(Profesor profesor);
}
