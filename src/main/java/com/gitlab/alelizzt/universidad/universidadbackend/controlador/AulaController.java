package com.gitlab.alelizzt.universidad.universidadbackend.controlador;

import com.gitlab.alelizzt.universidad.universidadbackend.exception.BadRequestException;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Aula;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.enumeradores.Pizarron;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class AulaController extends GenericController<Aula, AulaDAO>{
    public AulaController(AulaDAO service) {
        super(service);
        nombreEntidad="Aula";
    }

    @GetMapping("/pabellon")
    public List<Aula> buscarAulaPorPabellon(@RequestParam String pabellon){
        List<Aula> aulas = (List<Aula>) service.buscarAulasPorPabellon(pabellon);
        if(aulas.isEmpty()){
            throw new BadRequestException(String.format("No se encontraron aulas en el pabellon %s", pabellon));
        }
        return aulas;
    }

    @GetMapping("/pizarron")
    public List<Aula>buscarAulaPorPizarron(@RequestParam Pizarron tipoPizarron){
        List<Aula> aulas = (List<Aula>) service.buscarAulasPorPizarron(tipoPizarron);
        if(aulas.isEmpty()){
            throw new BadRequestException(String.format("No se encontraron aulas con pizarron %s", tipoPizarron.toString()));
        }
        return aulas;
    }

    @GetMapping("/num")
    public Aula buscarAulaPorNumero(@RequestParam Integer num){
        Aula aula = service.buscarAulaporNumero(num);
        if (aula == null){
            throw new BadRequestException(String.format("El aula con id %d no existe", num));
        }

        return aula;
    }
}
