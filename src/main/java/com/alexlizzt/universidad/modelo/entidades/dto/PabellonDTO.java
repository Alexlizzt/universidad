package com.alexlizzt.universidad.modelo.entidades.dto;

import com.alexlizzt.universidad.modelo.entidades.Direccion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "PabellonDTO",
        description = "Pabellones de la universidad"
)
public class PabellonDTO {
    @Schema(description = "Codigo del sistema", example = "10")
    private Integer codigo;

    @NotNull
    @Schema(description = "Medidas del Aula", example = "20.0")
    private Double mts2;

    @NotEmpty
    @Schema(description = "Nombre asignado al Pabellon en la universidad", example = "Artistico")
    private String nombre;

    @NotNull
    @Schema(description = "Dirección del pabellón")
    private Direccion direccion;
}
