package com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Aula;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;

public interface AulaDAO extends GenericDAO<Aula>{

    Iterable<Aula> buscarAulasPorPizarron(Pizarron tipoPizarron);
    Iterable<Aula> buscarAulasPorPabellon(String pabellon);
    Aula buscarAulaporNumero(int numAula);
}
