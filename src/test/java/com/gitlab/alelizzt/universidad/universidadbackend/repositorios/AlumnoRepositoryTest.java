package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Alumno;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
    Iterable<Persona> personas = alumnoRepository.saveAll(
            Arrays.asList(
                    alumno01(),
                    alumno02(),
                    alumno03())
    );

    Carrera save = carreraRepository.save(carrera01(false));

    personas.forEach(alumno -> ((Alumno)alumno).setCarrera(save));

    alumnoRepository.saveAll(personas);

    //when
    String carreraNombre = "Ingenieria en Sistemas";
    List<Persona> expected = (List<Persona>) ((AlumnoRepository) alumnoRepository).buscarAlumnosPorNombreCarrera(carreraNombre);

    //then
    assertThat(expected.size() == 3).isTrue();
  }

  @Test
  void buscarAlumnosPorNombreCarrerasinValores() {
    //given
    Iterable<Persona> personas = alumnoRepository.saveAll(
            Arrays.asList(
                    alumno01(),
                    alumno02(),
                    alumno03())
    );

    Carrera save = carreraRepository.save(carrera01(false));

    personas.forEach(alumno -> ((Alumno)alumno).setCarrera(save));

    alumnoRepository.saveAll(personas);

    //when
    String carreraNombre = "Ingenieria en Alimentos";
    List<Persona> expected = (List<Persona>) ((AlumnoRepository) alumnoRepository).buscarAlumnosPorNombreCarrera(carreraNombre);

    //then
    assertThat(expected.isEmpty()).isTrue();
  }
}