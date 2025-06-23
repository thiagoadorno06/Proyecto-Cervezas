package com.incade.poo.mozo.controller;

import com.incade.poo.mozo.dto.ItemDto;
import com.incade.poo.mozo.model.Cerveza;
import com.incade.poo.mozo.model.Item;
import com.incade.poo.mozo.model.Pedido;
import com.incade.poo.mozo.repository.CervezaJpaController;
import com.incade.poo.mozo.repository.ItemJpaController;
import java.util.ArrayList;
import java.util.List;

public class ItemController {
    ItemJpaController itemJpaController = new ItemJpaController();
    CervezaController cervezaController = new CervezaController();
  
    CervezaJpaController cervezaJpaController = new CervezaJpaController();
    
    public Item create(String cervezaNombre, Integer cantidad, Pedido pedido){
        
        Cerveza cerveza = cervezaJpaController.findCervezaByName(cervezaNombre);
        if (cerveza == null) {
            throw new IllegalArgumentException("Cerveza not found with name: " + cervezaNombre);
        }
        
        Item item = new Item();
        item.setCerveza(cerveza);
        item.setCantidad(cantidad);
        item.setPedido(pedido);
        item.setImporte(cerveza.getPrecio() * cantidad);
        
        itemJpaController.create(item);
        
        return item;
    }
    
    public ItemDto toDto(Item item){
        return new ItemDto(
                cervezaController.toDto(item.getCerveza()),
                item.getCantidad(),
                item.getImporte()
        );
    }
}
