package com.incade.poo.mozo.controller;

import com.incade.poo.mozo.dto.PedidoDto;
import com.incade.poo.mozo.model.Estado;
import com.incade.poo.mozo.model.Item;
import com.incade.poo.mozo.model.Mesa;
import com.incade.poo.mozo.model.Mozo;
import com.incade.poo.mozo.model.Pedido;
import com.incade.poo.mozo.repository.EstadoJpaController;
import com.incade.poo.mozo.repository.MesaJpaController;
import com.incade.poo.mozo.repository.MozoJpaController;
import com.incade.poo.mozo.repository.PedidoJpaController;
import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    PedidoJpaController pedidoJpaController = new PedidoJpaController();
    MesaJpaController mesaJpaController = new MesaJpaController();
    EstadoJpaController estadoJpaController = new EstadoJpaController();
    MozoJpaController mozoJpaController = new MozoJpaController();
    
    MozoController mozoController = new MozoController();
    ItemController itemController = new ItemController();
    
    public PedidoDto create(Integer mesaNumero, String cervezaNombre, Integer cantidad){
        
        Mesa mesa = mesaJpaController.findMesaByNumero(mesaNumero);
        Estado estadoPendiente = estadoJpaController.findEstadoByName("PENDIENTE");
        
        List<Item> items = new ArrayList<Item>();
        
        Pedido pedido = new Pedido();
        pedido.setMesa(mesa);
        pedido.setEstado(estadoPendiente);
        pedido.setItems(items);
        
        pedidoJpaController.create(pedido);
        
        Item item = itemController.create(cervezaNombre, cantidad, pedido);
        items.add(item);
       
        pedido.setItems(items);
        
        try {
            pedido.setTotal(item.getImporte());
            pedidoJpaController.edit(pedido);
        } catch (Exception ex) {
            System.getLogger(PedidoController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        return toDto(pedido);
    }
    
    public List<PedidoDto> getAll(){
        return pedidoJpaController.findPedidoEntities()
                .stream()
                .map(this::toDto)
                .toList();
    }
    
    public PedidoDto addItem(Long pedidoId, String cervezaNombre, Integer cantidad){
        
        if (pedidoId == null) {
            throw new IllegalArgumentException("Pedido ID cannot be null");
        }
        
        Pedido pedido = pedidoJpaController.findPedido(pedidoId);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido not found with ID: " + pedidoId);
        }
        Item item = itemController.create(cervezaNombre, cantidad, pedido);
        List<Item> items = pedido.getItems();
        items.add(item);
        pedido.setItems(items);
        pedido.setTotal(
                pedido.getItems()
                        .stream()
                        .mapToDouble(i -> i.getCerveza().getPrecio() * i.getCantidad())
                        .sum()
        );
        
        try {
            pedidoJpaController.edit(pedido);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Error pedido: " + pedidoId);
        }
        
        return toDto(pedido);
    }
    
    public void updateEstado(Long pedidoId, String estadoNombre){
        Estado estado = estadoJpaController.findEstadoByName(estadoNombre);
        Pedido pedido = pedidoJpaController.findPedido(pedidoId);
        pedido.setEstado(estado);
        
        try {
            pedidoJpaController.edit(pedido);
        } catch (Exception ex) {
            System.getLogger(PedidoController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public void takePedido(String email, Long pedidoId){
        
        Pedido pedido = pedidoJpaController.findPedido(pedidoId);
        
        Mozo mozo = mozoJpaController.findMozoByEmail(email);
        pedido.setMozo(mozo);
        if(mozo.getEmail() == null || pedido.getId() == null){
            throw new IllegalArgumentException();
        }
                
        try {
            pedidoJpaController.edit(pedido);
        } catch (Exception ex) {
            ex.printStackTrace();;
        }
    }
    
    public PedidoDto toDto(Pedido pedido){
        
        return new PedidoDto(
                pedido.getId(),
                pedido.getMesa().getNumero(), 
                pedido.getMozo() != null ? mozoController.toDto(pedido.getMozo()) : null, 
                pedido.getEstado().getNombre(), 
                pedido.getTotal(),
                pedido.getItems()
                        .stream()
                        .map(item -> itemController.toDto(item))
                        .toList()
        );
    }
    
}
