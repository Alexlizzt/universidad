package com.alexlizzt.universidad.modelo.entidades.dto;


import com.alexlizzt.universidad.modelo.entidades.Direccion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "AlumnoDTO",
        description = "Datos del alumno con carrera"
)
public class AlumnoDTO extends PersonaDTO {

    @Schema(description = "Nombre de la carrera", example = "Ingeniería en Sistemas")
    private String carreraNombre;

    public AlumnoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion, String carreraNombre) {
        super(id, nombre, apellido, dni, direccion);
        this.carreraNombre = carreraNombre;
    }
}

