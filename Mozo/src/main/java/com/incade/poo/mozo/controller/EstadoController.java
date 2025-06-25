package com.incade.poo.mozo.controller;

import com.incade.poo.mozo.dto.EstadoDto;
import com.incade.poo.mozo.model.Estado;
import com.incade.poo.mozo.repository.EstadoJpaController;
import java.util.List;

public class EstadoController {
    EstadoJpaController estadoJpaController = new EstadoJpaController();
    
    public EstadoDto create(String nombre){
        Estado estado = new Estado();
        estado.setNombre(nombre);
        estadoJpaController.create(estado);
        return toDto(estado);
    }
    
    public List<EstadoDto> getAll(){
        return estadoJpaController.findEstadoEntities()
                .stream()
                .map(this::toDto)
                .toList();
    }
    
    public Boolean deleteByName(String nombre){
        return estadoJpaController.destroyByName(nombre);
    }
    
    public EstadoDto toDto(Estado estado){
        return new EstadoDto(estado.getNombre());
    }
}
