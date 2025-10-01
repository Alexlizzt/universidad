package com.alexlizzt.universidad.modelo.entidades.mapper.mapstruct;

import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.dto.PersonaDTO;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface PersonaMapperConfig {

    void mapPersona(Persona persona, @MappingTarget PersonaDTO personaDTO);
}
