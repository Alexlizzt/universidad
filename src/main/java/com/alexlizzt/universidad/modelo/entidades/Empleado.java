package com.alexlizzt.universidad.modelo.entidades;

import com.alexlizzt.universidad.modelo.entidades.enumeradores.TipoEmpleado;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona {

    @Column(nullable = false)
    private BigDecimal sueldo;

    @Column(name = "tipo_empleado", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEmpleado tipoEmpleado;

    @ManyToOne(optional = true)
    @JoinColumn(name = "pabellon_id", foreignKey = @ForeignKey(name = "FK_PABELLON_ID"))
    private Pabellon pabellon;

    public Empleado() {
    }

    public Empleado(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo, TipoEmpleado tipoEmpleado) {
        super(id, nombre, apellido, dni, direccion);
        this.sueldo = sueldo;
        this.tipoEmpleado = tipoEmpleado;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Pabellon getPabellon() {
        return pabellon;
    }

    public void setPabellon(Pabellon pabellon) {
        this.pabellon = pabellon;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\t Empleado{" +
                "sueldo=" + sueldo +
                ", tipoEmpleado=" + tipoEmpleado +
                '}';
    }
}
