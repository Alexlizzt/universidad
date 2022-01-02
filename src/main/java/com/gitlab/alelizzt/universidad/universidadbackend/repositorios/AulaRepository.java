package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Aula;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AulaRepository  extends CrudRepository<Aula, Integer> {

    @Query("select a from Aula a where a.pizarron = ?1 ")
    Iterable<Aula> buscarAulasPorPizarron(Pizarron tipoPizarron);

    @Query("select a from Aula a, Pabellon p where p.nombre = ?1 ")
    Iterable<Aula> buscarAulasPorPabellon(String pabellon);

    @Query("select a from Aula a where a.nroAula = ?1 ")
    Aula buscarAulaporNumero(int numAula);
}
