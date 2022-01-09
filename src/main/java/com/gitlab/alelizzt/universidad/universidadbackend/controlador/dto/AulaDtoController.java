package com.gitlab.alelizzt.universidad.universidadbackend.controlador.dto;

import com.gitlab.alelizzt.universidad.universidadbackend.modelo.entidades.mapper.mapstruct.AulaMapperMS;
import com.gitlab.alelizzt.universidad.universidadbackend.servicios.contratos.AulaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aulas")
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "true")
public class AulaDtoController {

    
    private AulaDAO aulaDAO;
    @Autowired
    private AulaMapperMS mapper;
}
