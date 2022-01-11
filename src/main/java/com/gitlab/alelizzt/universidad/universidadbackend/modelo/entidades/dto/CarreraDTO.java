package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Carrera de la universidad", value = "Carrera", reference = "Carrera")
public class CarreraDTO {

    @ApiModelProperty(name = "Codigo del sistema", example = "5")
    private Integer codigo;
    @NotNull
    @NotEmpty(message = "Debe ingresar un valor")
    @Size(min = 0, max = 80)
    @ApiModelProperty(name = "Nombre de la carrera",example = "Licenciatura en Alimentos", required = true)
    private String nombre;
    @Positive(message = "El valor no puede ser negativo")
    @ApiModelProperty(name = "Cantidad de materias de toda la carrera", example = "55", required = true)
    private Integer cantidad_materias;
    @Positive
    @ApiModelProperty(name = "Cantidad de años de duracion de la carrera" , example = "5", required = true)
    private Integer cantidad_anios;

}
