package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Aula;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AulaRepository  extends CrudRepository<Aula, Integer> {

    Iterable<Aula> findAulasByPizarron(Pizarron tipoPizarron);

    @Query("select a from Aula a where a.pabellon.nombre = ?1")
    Iterable<Aula> buscarAulasPorPabellon(String pabellon);

    @Query("select a from Aula a where a.nroAula = ?1 ")
    Aula buscarAulaporNumero(int numAula);

    Aula findAulaByNroAula(Integer nroAula);
}
