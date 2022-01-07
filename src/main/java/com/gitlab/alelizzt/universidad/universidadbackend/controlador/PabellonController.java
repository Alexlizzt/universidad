package com.gitlab.alelizzt.universidad.universidadbackend.controlador;

import com.gitlab.alelizzt.universidad.universidadbackend.exception.BadRequestException;
import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.Pabellon;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.PabellonDAO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class PabellonController extends GenericController<Pabellon, PabellonDAO>{

    public PabellonController(PabellonDAO service) {
        super(service);
        nombreEntidad="Pabellon";
    }

    @GetMapping("/localodad")
    public List<Pabellon> buscarPabellonesPorLocalidad(@RequestParam String localidad){
        List<Pabellon> pabellones = (List<Pabellon>) service.buscarPabellonPorLocalidad(localidad);
        if(pabellones.isEmpty()){
            throw new BadRequestException(String.format("No se encontraron Pabellones en la localidad %s", localidad));
        }
        return pabellones;
    }

    @GetMapping("/nombre")
    public List<Pabellon> buscarPabellonPorNombre(@RequestParam String nombre){
        List<Pabellon> pabellones = (List<Pabellon>) service.buscarPabellonPorNombre(nombre);
        if(pabellones.isEmpty()){
            throw new BadRequestException(String.format("No se encontraron Pabellones con nombre %s", nombre));
        }
        return pabellones;
    }
}
