package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.repositorios.ProfesorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProfesorDAOImplTest {

    @Mock
    ProfesorRepository profesorRepository;

    @InjectMocks
    ProfesorDAOImpl profesorDAO;

    @Test
    void findProfesoresByCarrera() {
        //when
        profesorDAO.findProfesoresByCarrera(anyString());

        //then
        verify(profesorRepository).findProfesoresByCarrera(anyString());
    }
}