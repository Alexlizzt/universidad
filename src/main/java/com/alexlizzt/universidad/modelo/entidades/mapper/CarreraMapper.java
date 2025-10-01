package com.alexlizzt.universidad.modelo.entidades.mapper;

import com.alexlizzt.universidad.modelo.entidades.Carrera;
import com.alexlizzt.universidad.modelo.entidades.dto.CarreraDTO;

@Deprecated
public class CarreraMapper {
    public static CarreraDTO mapCarrera(Carrera carrera){
        CarreraDTO dto = new CarreraDTO();
        dto.setNombre(carrera.getNombre());
        dto.setCodigo(carrera.getId());
        dto.setCantidad_anios(carrera.getCantidadAnios());
        dto.setCantidad_materias(carrera.getCantidadMaterias());
        return dto;
    }


}
