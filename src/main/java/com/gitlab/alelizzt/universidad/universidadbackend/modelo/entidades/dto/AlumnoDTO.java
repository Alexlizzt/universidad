package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;


import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Direccion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Schema(
        name = "AlumnoDTO",
        description = "Persona base"
)
public class AlumnoDTO extends PersonaDTO {
    public AlumnoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        super(id, nombre, apellido, dni, direccion);
    }

}

