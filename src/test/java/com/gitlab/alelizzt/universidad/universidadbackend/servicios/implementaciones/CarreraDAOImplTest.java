package com.gitlab.alelizzt.universidad.universidadbackend.servicios.implementaciones;

import com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;
import com.gitlab.alelizzt.universidad.universidadbackend.repositorios.CarreraRepository;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.CarreraDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarreraDAOImplTest {

    CarreraDAO carreraDAO;
    CarreraRepository carreraRepository;

    @BeforeEach
    void setUp() {
        carreraRepository = mock(CarreraRepository.class);
        carreraDAO = new CarreraDAOImpl(carreraRepository);
    }

    @Test
    void findCarrerasByNombreContains() {
        //given
        String nombre = "Ingenieria";

        when(carreraRepository.findCarrerasByNombreContains(nombre))
                .thenReturn(Arrays.asList(carrera01(true),carrera03(true)));
        //when
        List<Carrera> expected = (List<Carrera>) carreraDAO.findCarrerasByNombreContains(nombre);

        //then
        assertThat(expected.get(0)).isEqualTo(carrera01(true));
        assertThat(expected.get(1)).isEqualTo(carrera03(true));

        verify(carreraRepository).findCarrerasByNombreContains(nombre);
    }

    @Test
    void findCarrerasByNombreContainsIgnoreCase() {
        //given
        String nombre = "ingenieria";

        when(carreraRepository.findCarrerasByNombreContainsIgnoreCase(nombre))
                .thenReturn(Arrays.asList(carrera01(true),carrera03(true)));
        //when
        List<Carrera> expected = (List<Carrera>) carreraDAO.findCarrerasByNombreContainsIgnoreCase(nombre);

        //then
        assertThat(expected.get(0)).isEqualTo(carrera01(true));
        assertThat(expected.get(1)).isEqualTo(carrera03(true));

        verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(nombre);
    }

    @Test
    void findCarrerasByCantidadAniosAfter() {
        //given
        int cantidad = 4;

        when(carreraRepository.findCarrerasByCantidadAniosAfter(cantidad))
                .thenReturn(Arrays.asList(carrera01(true),carrera03(true)));
        //when
        List<Carrera> expected = (List<Carrera>) carreraDAO.findCarrerasByCantidadAniosAfter(cantidad);

        //then
        assertThat(expected.get(0)).isEqualTo(carrera01(true));
        assertThat(expected.get(1)).isEqualTo(carrera03(true));

        verify(carreraRepository).findCarrerasByCantidadAniosAfter(cantidad);
    }
}