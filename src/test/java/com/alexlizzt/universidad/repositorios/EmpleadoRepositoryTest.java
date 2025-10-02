package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Empleado;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.enumeradores.TipoEmpleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.alexlizzt.universidad.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class EmpleadoRepositoryTest {
  @Autowired
  @Qualifier("repositorioEmpleados")
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

      List<Empleado> expected = (List<Empleado>) ((EmpleadoRepository) empleadoRepository).findEmpleadoByTipoEmpleado(TipoEmpleado.MANTENIMIENTO);

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
    List<Empleado> expected = (List<Empleado>) ((EmpleadoRepository) empleadoRepository).findEmpleadoByTipoEmpleado(TipoEmpleado.ENFERMERIA);

    //then
    assertThat(expected.isEmpty()).isTrue();

  }
}