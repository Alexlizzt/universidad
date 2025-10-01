package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.modelo.entidades.Aula;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.Pizarron;
import com.alexlizzt.universidad.repositorios.AulaRepository;
import com.alexlizzt.universidad.servicios.contratos.AulaDAO;
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
        return repository.findAulasByPizarron(tipoPizarron);
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
