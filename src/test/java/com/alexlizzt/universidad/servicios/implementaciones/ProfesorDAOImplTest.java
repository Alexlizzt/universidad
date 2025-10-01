package com.alexlizzt.universidad.servicios.implementaciones;

import com.alexlizzt.universidad.repositorios.ProfesorRepository;
import com.alexlizzt.universidad.servicios.contratos.ProfesorDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProfesorDAOImplTest {

    ProfesorRepository profesorRepository;
    @Autowired
    ProfesorDAO profesorDAO;
    @Test
    void findProfesoresByCarrera() {
        //when
        profesorDAO.findProfesoresByCarrera(anyString());

        //then
        verify(profesorRepository).findProfesoresByCarrera(anyString());
    }
}