package com.alexlizzt.universidad.servicios.contratos;

import com.alexlizzt.universidad.modelo.entidades.Profesor;

public interface ProfesorDAO extends PersonaDAO{
    Iterable<Profesor> findProfesoresByCarrera(String carrera);
}
