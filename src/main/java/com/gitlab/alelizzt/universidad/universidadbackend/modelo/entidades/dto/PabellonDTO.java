package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Direccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PabellonDTO {
    @Positive
    private Integer codigo;
    @NotEmpty(message = "ingrese tamaño en metros cuadrados")
    private Double mts2;
    @NotEmpty(message = "Ingrese el nombre del pabellon")
    private String nombre;
    @NotEmpty(message = "Debe ingresar un valor")
    private Direccion direccion;
}
