package com.alexlizzt.universidad.repositorios;

import com.alexlizzt.universidad.modelo.entidades.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.alexlizzt.universidad.datos.DatosDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonaRepositoryTest {

    @Autowired
    @Qualifier("repositorioAlumnos")
    PersonaRepository alumnoRepository;
    @Autowired
    CarreraRepository carreraRepository;
    @Autowired
    @Qualifier("repositorioEmpleados")
    PersonaRepository empleadoRepository;
    @Autowired
    @Qualifier("repositorioProfesores")
    PersonaRepository profesorRepository;

    @Test
    void buscarPorNombreYApellido() {
        //given
        Persona save = empleadoRepository.save(empleado01());

        //when
        Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(empleado01().getNombre(),
                empleado01().getApellido());

        //then
        assertThat(expected.get()).isInstanceOf(Empleado.class);
        assertThat(expected.get()).isEqualTo(save);
    }

    @Test
    void buscarPorDni() {
        //given
        Persona save = profesorRepository.save(profesor01());

        //when
        Optional<Persona> expected = profesorRepository.buscarPorDni(profesor01().getDni());
        
        //then
        assertThat(expected.get()).isInstanceOf(Profesor.class);
        assertThat(expected.get()).isEqualTo(save);
        assertThat(expected.get().getDni()).isEqualTo(save.getDni());
    }

    @Test
    void buscarPersonasPorApellido() {
        //given
        List<Persona> personas = Arrays.asList(alumno01(), alumno02(), alumno03());
        Carrera carrera = carreraRepository.save(carrera01(false));

        personas.forEach(p -> ((Alumno)p).setCarrera(carrera));

        alumnoRepository.saveAll(personas);


        //when
        String apellido = "Benitez";
        List<Persona> expected = (List<Persona>) alumnoRepository.buscarPersonasPorApellido(apellido);

        //then
        assertThat(expected.size() == 2).isTrue();
    }
}