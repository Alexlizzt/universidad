package com.alexlizzt.universidad.modelo.entidades.dto;

import com.alexlizzt.universidad.modelo.entidades.enumeradores.Pizarron;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "AulaDTO",
        description = "Aulas de la universidad"
)
public class AulaDTO {

    @Schema(description = "Codigo del sistema", example = "10")
    private Integer codigo;

    @NotNull
    @Positive(message = "El valor no puede ser negativo")
    @Schema(description = "Numeracion asignada al Aula en la universidad", example = "101")
    private Integer numero_aula;

    @NotBlank(message = "Debe ingresar medidas en metros cuadrados")
    @Schema(description = "Medidas del Aula", example = "20mts")
    private String medidas;

    @NotNull
    @Positive(message = "El valor no puede ser negativo")
    @Schema(description = "Cantidad de pupitres en el aula", example = "40")
    private Integer cantidad_pupitres;

    @NotNull(message = "Debe especificar el tipo de pizarrón")
    @Schema(
            description = "Tipo de pizarrón en el aula",
            example = "PIZARRA_BLANCA",
            allowableValues = {"PIZARRA_BLANCA", "PIZARRA_TIZA"}
    )
    private Pizarron tipo_pizarron;
}
