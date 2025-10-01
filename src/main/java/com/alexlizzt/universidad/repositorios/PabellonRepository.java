package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import org.springframework.data.repository.CrudRepository;

public interface PabellonRepository extends CrudRepository<Pabellon,Integer> {

    Iterable<Pabellon> findByDireccionLocalidad(String localidad);

    Iterable<Pabellon> findByNombre(String nombre);
}
