package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import com.alexlizzt.universidad.repositorios.PabellonRepository;
import com.alexlizzt.universidad.servicios.contratos.PabellonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PabellonDAOImpl extends GenericDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO {
    @Autowired
    public PabellonDAOImpl(PabellonRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> buscarPabellonPorLocalidad(String localidadPabellon) {
        return repository.findByDireccionLocalidad(localidadPabellon);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> buscarPabellonPorNombre(String nombrePabellon) {
        return repository.findByNombre(nombrePabellon);
    }
}
