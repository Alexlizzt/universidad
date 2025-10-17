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

    public Empleado(Integer id, String nombre, String apellido, String dni, Direccion direccion, BigDecimal sueldo,
            TipoEmpleado tipoEmpleado) {
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Empleado))
            return false;
        if (!super.equals(o))
            return false;

        Empleado empleado = (Empleado) o;

        if (sueldo != null ? !sueldo.equals(empleado.sueldo) : empleado.sueldo != null)
            return false;
        if (tipoEmpleado != empleado.tipoEmpleado)
            return false;

        // Para evitar problemas con Lazy Loading, comparamos solo el ID del pabellon si
        // no es null
        if (pabellon != null && empleado.pabellon != null) {
            return pabellon.getId().equals(empleado.pabellon.getId());
        } else {
            return pabellon == empleado.pabellon;
        }
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (sueldo != null ? sueldo.hashCode() : 0);
        result = 31 * result + (tipoEmpleado != null ? tipoEmpleado.hashCode() : 0);
        result = 31 * result + (pabellon != null && pabellon.getId() != null ? pabellon.getId().hashCode() : 0);
        return result;
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
