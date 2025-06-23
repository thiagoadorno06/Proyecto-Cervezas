package com.incade.poo.mozo.controller;

import com.incade.poo.mozo.dto.CervezaDto;
import com.incade.poo.mozo.model.Cerveza;
import com.incade.poo.mozo.repository.CervezaJpaController;
import com.incade.poo.mozo.repository.exceptions.NonexistentEntityException;
import java.util.List;

public class CervezaController {
    CervezaJpaController cervezaJpaController = new CervezaJpaController();
    
    public CervezaDto create(String nombre, Double precio, String descripcion){
        Cerveza cerveza = new Cerveza();
        cerveza.setNombre(nombre);
        cerveza.setPrecio(precio);
        cerveza.setDescripcion(descripcion);
        
        cervezaJpaController.create(cerveza);
        
        return toDto(cerveza);
    }
    
    public CervezaDto edit(String nombre, Double precio, String descripcion) throws Exception {
        Cerveza cerveza = cervezaJpaController.findCervezaByName(nombre);
    
        if (cerveza == null) {
            throw new NonexistentEntityException("No cerveza found with name: " + nombre);
        }
    
        cerveza.setNombre(nombre);
        cerveza.setPrecio(precio);
        cerveza.setDescripcion(descripcion);
    
        cervezaJpaController.edit(cerveza);
        return toDto(cerveza);
    }
    
    public List<CervezaDto> getAll(){
        return cervezaJpaController.findCervezaEntities()
                .stream()
                .map(this::toDto)
                .toList();
    }
    
    public CervezaDto getByName(String nombre){
        return toDto(cervezaJpaController.findCervezaByName(nombre));
    }
    
    public Boolean deleteByName(String nombre){
        return cervezaJpaController.destroyByName(nombre);
    }
    
    public CervezaDto toDto(Cerveza cerveza){
        return new CervezaDto(
                cerveza.getNombre(),
                cerveza.getPrecio(),
                cerveza.getDescripcion()
        );
    }
}
