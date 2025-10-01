package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.alexlizzt.universidad.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        List<Pabellon> expected = (List<Pabellon>) pabellonRepository.findByDireccionLocalidad(nombreLocalidad);

        //Then
        assertThat(expected.size() == 2).isTrue();
    }

    @Test
    void buscarPabellonPorNombre() {
        //When
        String nombrePabellon = "Principal";
        List<Pabellon> expected = (List<Pabellon>) pabellonRepository.findByNombre(nombrePabellon);

        //Then
        assertThat(expected.size() == 1).isTrue();
    }
}