package com.gitlab.alelizzt.universidad.universidadbackend.servicios.implementaciones;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.gitlab.alelizzt.universidad.universidadbackend.repositorios.AulaRepository;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AulaDAOImplTest {

    AulaDAO aulaDAO;
    AulaRepository aulaRepository;

    @BeforeEach
    void setUp() {
        aulaRepository = mock(AulaRepository.class);
        aulaDAO = new AulaDAOImpl(aulaRepository);
    }

    @Test
    void buscarAulasPorPizarron() {
        //when
        aulaDAO.buscarAulasPorPizarron(PIZARRA_BLANCA);

        //then
        verify(aulaRepository).buscarAulasPorPizarron(PIZARRA_BLANCA);
    }

    @Test
    void buscarAulasPorPabellon() {
        //when
        aulaDAO.buscarAulasPorPabellon(anyString());

        //then
        verify(aulaRepository).buscarAulasPorPabellon(anyString());
    }

    @Test
    void buscarAulaporNumero() {
        //given
        int numero = 1;

        aulaDAO.buscarAulaporNumero(numero);

        verify(aulaRepository).buscarAulaporNumero(numero);
    }
}