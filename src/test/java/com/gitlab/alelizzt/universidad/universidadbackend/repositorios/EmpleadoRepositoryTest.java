package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy.*;
import static com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.TipoEmpleado.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmpleadoRepositoryTest {
  @Autowired
  @Qualifier("empleadoRepository")
  PersonaRepository empleadoRepository;

    @Test
    void findEmpleadoByTipoEmpleado() {
      //given
      empleadoRepository.saveAll(
              Arrays.asList(
                      empleado01(),
                      empleado02()
              )
      );

      //when
      String tipoEmpleado = "MANTENIMIENTO";
      List<Persona> expected = (List<Persona>) ((EmpleadoRepository) empleadoRepository).findEmpleadoByTipoEmpleado(tipoEmpleado);

      //then
      assertThat(expected.size() == 1).isTrue();

    }

  @Test
  void findEmpleadoByTipoEmpleadoSinValores() {
    //given
    empleadoRepository.saveAll(
            Arrays.asList(
                    empleado01(),
                    empleado02()
            )
    );

    //when
    String tipoEmpleado = "ENFERMERIA";
    List<Persona> expected = (List<Persona>) ((EmpleadoRepository) empleadoRepository).findEmpleadoByTipoEmpleado(tipoEmpleado);

    //then
    assertThat(expected.isEmpty()).isTrue();

  }
}