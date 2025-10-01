package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.modelo.entidades.Profesor;
import com.alexlizzt.universidad.repositorios.PersonaRepository;
import com.alexlizzt.universidad.repositorios.ProfesorRepository;
import com.alexlizzt.universidad.servicios.contratos.ProfesorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {
    @Autowired
    public ProfesorDAOImpl(@Qualifier("repositorioProfesores")PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Profesor> findProfesoresByCarrera(String carrera) {
        return ((ProfesorRepository)repository).findProfesoresByCarrera(carrera);
    }
}
