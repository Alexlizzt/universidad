package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.Persona;
import com.alexlizzt.universidad.modelo.entidades.Profesor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.alexlizzt.universidad.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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

        personas.forEach(profesor -> ((Profesor)profesor).setCarreras(carreras));

        profesorRepository.saveAll(personas);

        //when
        String carreraNombre = "Ingenieria en Sistemas";
        List<Profesor> expected = (List<Profesor>) ((ProfesorRepository) profesorRepository).findProfesoresByCarrera(carreraNombre);

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

        personas.forEach(profesor -> ((Profesor)profesor).setCarreras(carreras));

        profesorRepository.saveAll(personas);

        //when
        String carreraNombre = "Ingenieria Industrial";
        List<Profesor> expected = (List<Profesor>) ((ProfesorRepository) profesorRepository).findProfesoresByCarrera(carreraNombre);

        //then
        assertThat(expected.isEmpty()).isTrue();
    }
}