package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AulaDTO {
    @NotNull
    private Integer codigo;
    @Positive(message = "El valor no puede ser negativo")
    private Integer nroAula;
    @NotEmpty(message = "Debe ingresar medidas en metros cuadrados")
    private String medidas;
    @Positive(message = "El valor no puede ser negativo")
    private Integer cantidadPupitres;
    @NotNull
    @NotEmpty(message = "Debe ingresar un valor")
    private Pizarron pizarron;
}
