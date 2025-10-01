package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.repositorios.AlumnoRepository;
import com.alexlizzt.universidad.servicios.contratos.AlumnoDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;


@SpringBootTest
class AlumnoDAOImplTest {

    AlumnoRepository alumnoRepository;
    @Autowired
    AlumnoDAO alumnoDAO;

    @Test
    void buscarAlumnosPorNombreCarrera() {
        //When
        alumnoDAO.buscarAlumnosPorNombreCarrera(anyString());

        //Then
        verify(alumnoRepository).buscarAlumnosPorNombreCarrera(anyString());
    }
}