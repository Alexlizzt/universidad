package com.alexlizzt.universidad.servicios.contratos;

import com.alexlizzt.universidad.modelo.entidades.Pabellon;

public interface PabellonDAO extends GenericDAO<Pabellon>{
    Iterable<Pabellon> buscarPabellonPorLocalidad(String localidadPabellon);
    Iterable<Pabellon> buscarPabellonPorNombre(String nombrePabellon);
}
