package com.incade.poo.mozo.controller;

import com.incade.poo.mozo.dto.MesaDto;
import com.incade.poo.mozo.model.Mesa;
import com.incade.poo.mozo.repository.MesaJpaController;
import java.util.List;

public class MesaController {
    MesaJpaController mesaJpaController = new MesaJpaController();
    
    public MesaDto create(Long numero){
        Mesa mesa = new Mesa();
        mesa.setNumero(numero);
        mesaJpaController.create(mesa);
        return toDto(mesa);
    }
    
    public List<MesaDto> getAll(){
        return mesaJpaController.findMesaEntities()
                .stream()
                .map(this::toDto)
                .toList();
    }
    
    public MesaDto getByNumber(Long numero){
        return toDto(mesaJpaController.findMesaByNumero(numero));
    }
    
    public MesaDto toDto(Mesa mesa){
        return new MesaDto(mesa.getNumero());
    }
}
