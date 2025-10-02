package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.repositorios.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class AlumnoDAOImplTest {

    @Mock
    AlumnoRepository alumnoRepository;

    @InjectMocks
    AlumnoDAOImpl alumnoDAO;

    @Test
    void buscarAlumnosPorNombreCarrera() {
        //When
        alumnoDAO.buscarAlumnosPorNombreCarrera(anyString());

        //Then
        verify(alumnoRepository).buscarAlumnosPorNombreCarrera(anyString());
    }
}