package com.gitlab.alelizzt.universidad.universidadbackend.repositorios;

import com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Carrera;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Persona;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Profesor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.gitlab.alelizzt.universidad.universidadbackend.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProfesorRepositoryTest {

    @Autowired
    @Qualifier("repositorioProfesores")
    PersonaRepository profesorRepository;
    @Autowired
    CarreraRepository carreraRepository;

    @Test
    void findProfesoresByCarrera() {
        //given
        Iterable<Persona> personas = profesorRepository.saveAll(
                Arrays.asList(
                        profesor01(),
                        profesor02()
                )
        );

        Carrera carrera01 = carreraRepository.save(carrera01(false));

        Set<Carrera> carreras = new HashSet<>();
        carreras.add(carrera01);

        personas.forEach(profesor -> ((Profesor)profesor).setCarrera(carreras));

        profesorRepository.saveAll(personas);

        //when
        String carreraNombre = "Ingenieria en Sistemas";
        List<Persona> expected = (List<Persona>) ((ProfesorRepository) profesorRepository).findProfesoresByCarrera(carreraNombre);

        //then
        assertThat(expected.size() == 2).isTrue();
    }

    @Test
    void findProfesoresByCarreraWithoutValues() {
        //given
        Iterable<Persona> personas = profesorRepository.saveAll(
                Arrays.asList(
                        profesor01(),
                        profesor02()
                )
        );

        Carrera carrera01 = carreraRepository.save(carrera01(false));

        Set<Carrera> carreras = new HashSet<>();
        carreras.add(carrera01);

        personas.forEach(profesor -> ((Profesor)profesor).setCarrera(carreras));

        profesorRepository.saveAll(personas);

        //when
        String carreraNombre = "Ingenieria Industrial";
        List<Persona> expected = (List<Persona>) ((ProfesorRepository) profesorRepository).findProfesoresByCarrera(carreraNombre);

        //then
        assertThat(expected.isEmpty()).isTrue();
    }
}