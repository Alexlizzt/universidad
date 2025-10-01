package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.modelo.entidades.Empleado;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.TipoEmpleado;
import com.alexlizzt.universidad.repositorios.EmpleadoRepository;
import com.alexlizzt.universidad.repositorios.PersonaRepository;
import com.alexlizzt.universidad.servicios.contratos.EmpleadoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {

    @Autowired
    public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados") PersonaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Empleado> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
        return ((EmpleadoRepository)repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
    }
}
