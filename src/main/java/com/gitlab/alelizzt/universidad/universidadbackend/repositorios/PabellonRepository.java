package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PabellonRepository extends CrudRepository<Pabellon,Integer> {
    @Query("select p from Pabellon p join fetch p.direccion d where d.localidad = ?1")
    Iterable<Pabellon> buscarPabellonPorLocalidad(String localidadPabellon);

    @Query("select p from Pabellon p where p.nombre = ?1")
    Iterable<Pabellon> buscarPabellonPorNombre(String nombrePabellon);
}
