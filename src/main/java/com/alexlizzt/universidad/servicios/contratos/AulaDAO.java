package com.alexlizzt.universidad.servicios.contratos;

import com.alexlizzt.universidad.modelo.entidades.Aula;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.Pizarron;

public interface AulaDAO extends GenericDAO<Aula>{

    Iterable<Aula> buscarAulasPorPizarron(Pizarron tipoPizarron);
    Iterable<Aula> buscarAulasPorPabellon(String pabellon);
    Aula buscarAulaporNumero(int numAula);
}
