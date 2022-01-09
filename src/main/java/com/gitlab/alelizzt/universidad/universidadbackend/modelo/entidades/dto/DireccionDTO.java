package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionDTO {
    @NotEmpty
    private String calle;
    @NotEmpty
    private String numero;
    private String codigoPostal;
    private String dpto;
    private String piso;
    @NotEmpty
    private String localidad;
}
