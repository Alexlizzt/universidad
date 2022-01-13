package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;


import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Direccion;
import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@ApiModel(description = "Alumno de la universidad", value = "Alumno", reference = "Alumno", discriminator = "tipo")
@Schema(description = "Alumno Entidad")
public class AlumnoDTO extends PersonaDTO {
    //TODO: Incluir JsonSubTypes en Swagger
    public AlumnoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        super(id, nombre, apellido, dni, direccion);
    }

}

