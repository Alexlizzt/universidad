package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "CarreraDTO",
        description = "Carrera de la universidad"
)
public class CarreraDTO {

    @Schema(description = "Codigo del sistema", example = "5")
    private Integer codigo;

    @NotBlank(message = "Debe ingresar un valor")
    @Size(max = 80, message = "El nombre no debe superar los 80 caracteres")
    @Schema(name = "Nombre de la carrera",example = "Licenciatura en Alimentos")
    private String nombre;

    @Positive(message = "El valor no puede ser negativo")
    @Schema(name = "Cantidad de materias de toda la carrera", example = "55")
    private Integer cantidad_materias;

    @Positive
    @Schema(name = "Cantidad de años de duracion de la carrera" , example = "5")
    private Integer cantidad_anios;

}
