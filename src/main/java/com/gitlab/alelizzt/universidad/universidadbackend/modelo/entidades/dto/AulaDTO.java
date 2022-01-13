package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Aulas de la universidad", value = "Aula", reference = "Aula")
public class AulaDTO {
    @ApiModelProperty(name = "Codigo del sistema", example = "10")
    private Integer codigo;
    @NotNull
    @Positive(message = "El valor no puede ser negativo")
    @ApiModelProperty(name = "Numeracion asignada al Aula en la universidad", example = "101", required = true)
    private Integer numero_aula;
    @NotEmpty(message = "Debe ingresar medidas en metros cuadrados")
    @ApiModelProperty(name = "Medidas del Aula", example = "20mts", required = true)
    private String medidas;
    @Positive(message = "El valor no puede ser negativo")
    @ApiModelProperty(name = "Cantidad de pupitres en el aula", example = "40", required = true)
    private Integer cantidad_pupitres;
    //TODO: DDocumentar valores Enum en Swagger
    //@NotEmpty(message = "Debe ingresar un valor")
    @ApiModelProperty(name = "Tipo de pizarron que se encuentra en el aula", example = "PIZARRA_BLANCA")
    private Pizarron tipo_pizarron;
}
