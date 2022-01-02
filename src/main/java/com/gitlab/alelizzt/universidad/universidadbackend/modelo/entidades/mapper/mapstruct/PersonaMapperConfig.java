package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto.PersonaDTO;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig
public interface PersonaMapperConfig {

    void mapPersona(Persona persona, @MappingTarget PersonaDTO personaDTO);
}
