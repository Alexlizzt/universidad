package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.repositorios.AulaRepository;
import com.alexlizzt.universidad.servicios.contratos.AulaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.alexlizzt.universidad.modelo.entidades.enumeradores.Pizarron.*;
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
        verify(aulaRepository).findAulasByPizarron(PIZARRA_BLANCA);
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