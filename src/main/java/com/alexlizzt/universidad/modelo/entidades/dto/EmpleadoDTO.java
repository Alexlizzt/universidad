package com.alexlizzt.universidad.modelo.entidades.dto;

import com.alexlizzt.universidad.modelo.entidades.Direccion;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.TipoEmpleado;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Schema(
        description = "Empleado de la universidad"
)
public class EmpleadoDTO extends PersonaDTO{

    @Schema(name = "Sueldo del empleado" , example = "5000.00")
    private BigDecimal sueldo;

    @Schema(name = "Tipo de empleado", example = "MANTENIMIENTO")
    private TipoEmpleado tipo_empleado;

    public EmpleadoDTO(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo, TipoEmpleado tipo_empleado) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipo_empleado = tipo_empleado;
    }
}
