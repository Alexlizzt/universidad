package com.alexlizzt.universidad.modelo.entidades;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "alumnos")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Alumno extends Persona{

    @ManyToOne(
            optional = false,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "carrera_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","alumnos"})
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
    public String toString() {
        return super.toString() +
           " Alumno{carrera=" + (carrera != null ? carrera.getNombre() : "Ninguna") + "}";
    }
}
