package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Alumno;
import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.alexlizzt.universidad.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AlumnoRepositoryTest {

  @Autowired
  @Qualifier("repositorioAlumnos")
  PersonaRepository alumnoRepository;
  @Autowired
  CarreraRepository carreraRepository;
  @Test
  void buscarAlumnosPorNombreCarrera() {
    //given
      List<Persona> personas = Arrays.asList(alumno01(), alumno02(), alumno03());
      Carrera carrera = carreraRepository.save(carrera01(false));

      personas.forEach(p -> ((Alumno)p).setCarrera(carrera));

      alumnoRepository.saveAll(personas);

    //when
    String carreraNombre = "Ingenieria en Sistemas";
    List<Alumno> expected = (List<Alumno>) ((AlumnoRepository) alumnoRepository).buscarAlumnosPorNombreCarrera(carreraNombre);

    //then
    assertThat(expected.size() == 3).isTrue();
  }

  @Test
  void buscarAlumnosPorNombreCarrerasinValores() {
    //given
      List<Persona> personas = Arrays.asList(alumno01(), alumno02(), alumno03());
      Carrera carrera = carreraRepository.save(carrera01(false));

      personas.forEach(p -> ((Alumno)p).setCarrera(carrera));

      alumnoRepository.saveAll(personas);

    //when
    String carreraNombre = "Ingenieria en Alimentos";
    List<Alumno> expected = (List<Alumno>) ((AlumnoRepository) alumnoRepository).buscarAlumnosPorNombreCarrera(carreraNombre);

    //then
    assertThat(expected.isEmpty()).isTrue();
  }
}