package com.gitlab.alelizzt.universidad.universidadbackend.servicios.implementaciones;

import com.gitlab.alelizzt.universidad.universidadbackend.repositorios.PabellonRepository;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PabellonDAOImplTest {

    PabellonDAO pabellonDAO;
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
        verify(pabellonRepository).buscarPabellonPorLocalidad(anyString());
    }

    @Test
    void buscarPabellonPorNombre() {
        //when
        pabellonDAO.buscarPabellonPorNombre(anyString());

        //then
        verify(pabellonRepository).buscarPabellonPorNombre(anyString());

    }
}