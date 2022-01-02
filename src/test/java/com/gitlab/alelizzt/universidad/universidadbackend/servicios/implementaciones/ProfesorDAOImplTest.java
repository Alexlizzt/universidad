package com.gitlab.alelizzt.universidad.universidadbackend.servicios.implementaciones;

import com.gitlab.alelizzt.universidad.universidadbackend.repositorios.ProfesorRepository;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.ProfesorDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ProfesorDAOImplTest {

    @MockBean
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