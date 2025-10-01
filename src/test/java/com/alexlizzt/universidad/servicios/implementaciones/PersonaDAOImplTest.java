package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.repositorios.AlumnoRepository;
import com.alexlizzt.universidad.servicios.contratos.PersonaDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonaDAOImplTest {

    PersonaDAO personaDAO;
    @Mock
    AlumnoRepository alumnoRepository;

    @BeforeEach
    void setUp() {
        personaDAO = new PersonaDAOImpl(alumnoRepository);
    }

    @Test
    void buscarPorNombreYApellido() {
        //when
        personaDAO.buscarPorNombreYApellido(anyString(), anyString());

        //then
        verify(alumnoRepository).buscarPorNombreYApellido(anyString(), anyString());
    }

    @Test
    void buscarPorDni() {
        //when
        personaDAO.buscarPorDni(anyString());

        //then
        verify(alumnoRepository).buscarPorDni(anyString());
    }

    @Test
    void buscarPersonasPorApellido() {
        //when
        personaDAO.buscarPersonasPorApellido(anyString());

        //then
        verify(alumnoRepository).buscarPersonasPorApellido(anyString());
    }
}