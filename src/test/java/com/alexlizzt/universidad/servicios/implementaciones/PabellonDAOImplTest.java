package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.repositorios.PabellonRepository;
import com.alexlizzt.universidad.servicios.contratos.PabellonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PabellonDAOImplTest {

    PabellonDAOImpl pabellonDAO;
    PabellonRepository pabellonRepository;

    @BeforeEach
    void setUp() {
        pabellonRepository = mock(PabellonRepository.class);
        pabellonDAO = new PabellonDAOImpl(pabellonRepository);
    }

    @Test
    void buscarPabellonPorLocalidad() {
        //when
        pabellonDAO.buscarPabellonPorLocalidad(anyString());

        //then
        verify(pabellonRepository).findByDireccionLocalidad(anyString());
    }

    @Test
    void buscarPabellonPorNombre() {
        //when
        pabellonDAO.buscarPabellonPorNombre(anyString());

        //then
        verify(pabellonRepository).findByNombre(anyString());

    }
}