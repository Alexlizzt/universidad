package com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;

public interface PabellonDAO extends GenericDAO<Pabellon>{
    Iterable<Pabellon> buscarPabellonPorLocalidad(String localidadPabellon);
    Iterable<Pabellon> buscarPabellonPorNombre(String nombrePabellon);
}
