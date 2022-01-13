package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Alumno;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Direccion;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Profesor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AlumnoDTO.class, name = "alumno"),
        @JsonSubTypes.Type(value = ProfesorDTO.class, name = "profesor"),
        @JsonSubTypes.Type(value = EmpleadoDTO.class, name = "empleado")
})
public abstract class PersonaDTO {

    @ApiModelProperty(name = "Codigo del sistema", example = "7")
    private Integer id;
    @NotEmpty
    @ApiModelProperty(name = "Nombre de la persona", example = "Natalia")
    private String nombre;
    @NotEmpty
    @ApiModelProperty(name = "Apellido de la persona", example = "Lafourcade Silva")
    private String apellido;
    @Pattern(regexp = "[0-9]+")
    @Size(min = 1, max = 10)
    @ApiModelProperty(name = "Identificacion de la persona", example = "1044878239")
    private String dni;
    private Direccion direccion;
}
