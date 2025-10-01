package com.alexlizzt.universidad.modelo.entidades.dto;


import com.alexlizzt.universidad.modelo.entidades.Direccion;
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

