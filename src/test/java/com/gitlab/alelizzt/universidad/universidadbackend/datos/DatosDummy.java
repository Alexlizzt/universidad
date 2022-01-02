package com.gitlab.alelizzt.universidad.universidadbackend.datos;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.*;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado;

import java.math.BigDecimal;

import static com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron.*;
import static com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado.*;

public class DatosDummy {

    public static Carrera carrera01(boolean conId) {
        Carrera ingsistemas = (conId) ? new Carrera(1, "Ingenieria en Sistemas",50,5):
        new Carrera(null, "Ingenieria en Sistemas",50,5);
        return ingsistemas;
    }

    public static Carrera carrera02(){
        return new Carrera(null, "Licenciatura en Sistemas",45,4);
    }

    public static Carrera carrera03(boolean conId){
        Carrera ingindustrial = (conId) ? new Carrera(3, "Ingenieria Industrial",60,5):
         new Carrera(null, "Ingenieria Industrial",60,5);
        return ingindustrial;
    }

    public static Persona empleado01() {
        return new Empleado(null, "Laura","Lopez","91234123", new Direccion(), new BigDecimal("46750.70"), ADMINISTRATIVO);
    }

    public static Persona empleado02() {
        return new Empleado(null, "Leandro","Gutierrez","61330103", new Direccion(), new BigDecimal("26750.70"), MANTENIMIENTO);
    }

    public static Persona profesor01() {
        return new Profesor(null, "Mariana", "Gonzalez","99887711", new Direccion(),new BigDecimal("6000.00"));
    }

    public static Persona profesor02() {
        return new Profesor(null, "Daniela", "Cespedes","88552266", new Direccion(),new BigDecimal("2000.00"));
    }

    public static Persona alumno01() {
        return new Alumno(null,"Jose","Martinez","975611723",new Direccion());
    }

    public static Persona alumno02() {
        return new Alumno(null,"Fernando","Benitez","575001722",new Direccion());
    }

    public static Persona alumno03() {
        return new Alumno(null,"Joaquin","Benitez","13611700",new Direccion());
    }

    public static Pabellon pabellon01() {
        return new Pabellon(null,500.2,"Principal",new Direccion("Calle Falsa","12","12346561","","1","Martires"));
    }

    public static Pabellon pabellon02() {
        return new Pabellon(null,500.2,"Ejecutivo",new Direccion("Calle Falsa","17","12346561","","1","Martires"));
    }

    public static Aula aula01() {
        return new Aula(null,1,"50mts",45, PIZARRA_BLANCA);
    }

    public static Aula aula02() {
        return new Aula(null,101,"50mts",35, PIZARRA_TIZA);
    }
    public static Aula aula03() {
        return new Aula(null,102,"50mts",50, PIZARRA_BLANCA);
    }
    public static Aula aula04() {
        return new Aula(null,103,"50mts",55, PIZARRA_BLANCA);
    }
}
