package com.gitlab.alelizzt.universidad.universidadbackend.servicios.implementaciones;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.gitlab.alelizzt.universidad.universidadbackend.repositorios.PabellonRepository;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PabellonDAO;
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
        return repository.buscarPabellonPorLocalidad(localidadPabellon);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Pabellon> buscarPabellonPorNombre(String nombrePabellon) {
        return repository.buscarPabellonPorNombre(nombrePabellon);
    }
}
