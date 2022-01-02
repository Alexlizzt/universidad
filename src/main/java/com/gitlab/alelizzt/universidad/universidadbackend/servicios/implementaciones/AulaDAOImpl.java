package com.gitlab.alelizzt.universidad.universidadbackend.servicios.implementaciones;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Aula;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.gitlab.alelizzt.universidad.universidadbackend.repositorios.AulaRepository;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AulaDAOImpl extends GenericDAOImpl<Aula, AulaRepository> implements AulaDAO {

    @Autowired
    public AulaDAOImpl(AulaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> buscarAulasPorPizarron(Pizarron tipoPizarron) {
        return repository.buscarAulasPorPizarron(tipoPizarron);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Aula> buscarAulasPorPabellon(String pabellon) {
        return repository.buscarAulasPorPabellon(pabellon);
    }

    @Override
    @Transactional(readOnly = true)
    public Aula buscarAulaporNumero(int numAula) {
        return repository.buscarAulaporNumero(numAula);
    }
}
