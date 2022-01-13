package com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Direccion;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@ApiModel(description = "Empleado de la universidad", value = "Empleado", reference = "Empleado")
public class EmpleadoDTO extends PersonaDTO{

    @ApiModelProperty(name = "Sueldo del empleado" , example = "5000.00")
    private BigDecimal sueldo;
    @ApiModelProperty(name = "Tipo de empleado", example = "MANTENIMIENTO")
    private TipoEmpleado tipo_empleado;

    public EmpleadoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo, TipoEmpleado tipo_empleado) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipo_empleado = tipo_empleado;
    }
}
