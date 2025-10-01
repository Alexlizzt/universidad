package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Aula;
import com.alexlizzt.universidad.modelo.entidades.Pabellon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.alexlizzt.universidad.datos.DatosDummy.*;
import static com.alexlizzt.universidad.modelo.entidades.enumeradores.Pizarron.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AulaRepositoryTest {

  @Autowired
  AulaRepository aulaRepository;

  @Autowired
  PabellonRepository pabellonRepository;
  
  @AfterEach
  void tearDown() {
    aulaRepository.deleteAll();
  }

  @Test
    void buscarAulasPorPizarron() {
    //Given
    Iterable<Aula> aulas = aulaRepository.saveAll(
            Arrays.asList(
                    aula01(),
                    aula02(),
                    aula03(),
                    aula04()
            )
    );

    //when
    List<Aula> expected = (List<Aula>) aulaRepository.findAulasByPizarron(PIZARRA_BLANCA);

    //Then
    assertThat(expected.size() == 3).isTrue();
  }

    @Test
    void buscarAulasPorPabellon() {
      //Given
      Iterable<Aula> aulas = aulaRepository.saveAll(
              Arrays.asList(
                      aula01(),
                      aula02(),
                      aula03(),
                      aula04()
              )
      );

      Pabellon save = pabellonRepository.save(pabellon01());

      aulas.forEach(aula -> ((Aula)aula).setPabellon(save));

      aulaRepository.saveAll(aulas);

      //when

      String pabellonNombre = "Principal";
      List<Aula> expected = (List<Aula>) aulaRepository.buscarAulasPorPabellon(pabellonNombre);

      //then
      assertThat(expected.size() == 4).isTrue();
    }

    @Test
    void buscarAulaporNumero() {
      //Given
      List<Aula> aulas = (List<Aula>) aulaRepository.saveAll(
              Arrays.asList(
                      aula01(),
                      aula02(),
                      aula03(),
                      aula04()
              )
      );

      //when
      int numeroAula = 101;
      Aula expected = aulaRepository.buscarAulaporNumero(numeroAula);

      //then
      assertThat(expected).isInstanceOf(Aula.class);
      assertThat(expected).isEqualTo(aulas.get(1));

    }
}