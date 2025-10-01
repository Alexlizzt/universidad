package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.repositorios.AlumnoRepository;
import com.alexlizzt.universidad.repositorios.PersonaRepository;
import com.alexlizzt.universidad.servicios.contratos.AlumnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {

    @Autowired
    public AlumnoDAOImpl(@Qualifier("repositorioAlumnos")PersonaRepository repository) {
        super(repository);
    }


    @Override
    public Iterable<Alumno> buscarAlumnosPorNombreCarrera(String nombre) {
        return ((AlumnoRepository)repository).buscarAlumnosPorNombreCarrera(nombre);
    }
}
