package com.alexlizzt.universidad.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Alumno extends Persona {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "alumnos" })
    private Carrera carrera;

    public Alumno() {
    }

    public Alumno(Integer id, String nombre, String apellido, String dni, Direccion direccion) {
        super(id, nombre, apellido, dni, direccion);
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Alumno))
            return false;
        if (!super.equals(o))
            return false;

        Alumno alumno = (Alumno) o;

        // Comparamos por carrera solo por ID para evitar problemas de carga perezosa
        return carrera != null && alumno.carrera != null
                ? carrera.getId().equals(alumno.carrera.getId())
                : carrera == alumno.carrera;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (carrera != null && carrera.getId() != null ? carrera.getId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                " Alumno{carrera=" + (carrera != null ? carrera.getNombre() : "Ninguna") + "}";
    }
}
