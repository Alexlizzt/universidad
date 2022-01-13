package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Direccion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Pabellones de la universidad", value = "Pabellon", reference = "Pabellon")
public class PabellonDTO {
    @ApiModelProperty(name = "Codigo del sistema", example = "10")
    private Integer codigo;
    @ApiModelProperty(name = "Medidas del Aula", example = "20mts", required = true)
    private Double mts2;
    @ApiModelProperty(name = "Nombre asignado al Pabellon en la universidad", example = "Artistico", required = true)
    private String nombre;
    private Direccion direccion;
}
