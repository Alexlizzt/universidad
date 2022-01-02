package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PabellonRepositoryTest {

    @Autowired
    PabellonRepository pabellonRepository;

  @BeforeEach
  void setUp() {
      //Given
      pabellonRepository.saveAll(
              Arrays.asList(
                      pabellon01(),
                      pabellon02()
              )
      );
  }

  @AfterEach
  void tearDown() {
      pabellonRepository.deleteAll();
  }

    @Test
    void buscarPabellonPorLocalidad() {
        //When
        String nombreLocalidad = "Martires";
        List<Pabellon> expected = (List<Pabellon>) pabellonRepository.buscarPabellonPorLocalidad(nombreLocalidad);

        //Then
        assertThat(expected.size() == 2).isTrue();
    }

    @Test
    void buscarPabellonPorNombre() {
        //When
        String nombrePabellon = "Principal";
        List<Pabellon> expected = (List<Pabellon>) pabellonRepository.buscarPabellonPorNombre(nombrePabellon);

        //Then
        assertThat(expected.size() == 1).isTrue();
    }
}