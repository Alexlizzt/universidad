package com.alexlizzt.universidad.modelo.entidades.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.alexlizzt.universidad.modelo.entidades.Direccion;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Schema(
        description = "Persona base de la que heredan Alumno, Profesor y Empleado",
        discriminatorProperty = "tipo",
        oneOf = {AlumnoDTO.class, ProfesorDTO.class, EmpleadoDTO.class}
)
public abstract class PersonaDTO {

    @Schema(description = "Código del sistema", example = "7")
    private Integer id;

    @NotEmpty
    @Schema(description = "Nombre de la persona", example = "Natalia")
    private String nombre;

    @NotEmpty
    @Schema(description = "Apellido de la persona", example = "Lafourcade Silva")
    private String apellido;

    @Pattern(regexp = "[0-9]+")
    @Size(min = 1, max = 10)
    @Schema(description = "Documento nacional de identidad", example = "1044878239")
    private String dni;

    @Schema(description = "Dirección de la persona")
    private Direccion direccion;
}
