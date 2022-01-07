package com.gitlab.alelizzt.universidad.universidadbackend.servicios.implementaciones;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.repositorios.EmpleadoRepository;
import com.gitlab.alelizzt.universidad.universidadbackend.repositorios.PersonaRepository;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {


    public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados") PersonaRepository repository) {
        super(repository);
    }

    @Override
    public Iterable<Persona> findEmpleadoByTipoEmpleado(String tipoEmpleado) {
        return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }

}
