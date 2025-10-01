package com.alexlizzt.universidad.modelo.entidades;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Direccion implements Serializable {

    private String calle;
    private String numero;
    private String codigoPostal;
    private String dpto;
    private String piso;
    private String localidad;

    public Direccion() {
    }

    public Direccion(String calle, String numero, String codigoPostal, String dpto, String piso, String localidad) {
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.dpto = dpto;
        this.piso = piso;
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Direccion)) return false;
        Direccion d = (Direccion) o;
        return Objects.equals(calle, d.calle) &&
            Objects.equals(numero, d.numero) &&
            Objects.equals(codigoPostal, d.codigoPostal) &&
            Objects.equals(dpto, d.dpto) &&
            Objects.equals(piso, d.piso) &&
            Objects.equals(localidad, d.localidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(calle, numero, codigoPostal, dpto, piso, localidad);
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "calle='" + calle + '\'' +
                ", numero='" + numero + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", dpto='" + dpto + '\'' +
                ", piso='" + piso + '\'' +
                ", localidad='" + localidad + '\'' +
                '}';
    }
}
