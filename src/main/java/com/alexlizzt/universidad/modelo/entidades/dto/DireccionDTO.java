package com.alexlizzt.universidad.modelo.entidades.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(name = "DireccionDTO", description = "Dirección de una persona o edificio en la universidad")
public class DireccionDTO {

    @NotBlank(message = "La calle no puede estar vacía")
    @Schema(description = "Nombre de la calle", example = "Av. Siempre Viva")
    private String calle;

    @NotBlank(message = "El número de la calle no puede estar vacío")
    @Schema(description = "Número de la calle", example = "742")
    private String numero;

    @Schema(description = "Código postal de la dirección", example = "1406")
    private String codigoPostal;

    @Schema(description = "Departamento, si aplica", example = "B")
    private String dpto;

    @Schema(description = "Piso del edificio, si aplica", example = "3")
    private String piso;

    @NotBlank(message = "La localidad no puede estar vacía")
    @Schema(description = "Localidad o ciudad", example = "Springfield")
    private String localidad;
}

