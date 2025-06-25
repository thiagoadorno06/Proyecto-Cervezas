package com.incade.poo.mozo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Double total;
    
    @ManyToOne
    private Mesa mesa;
    
    @ManyToOne
    private Mozo mozo;
    
    @ManyToOne
    private Estado estado;

    @OneToMany(mappedBy = "pedido")
    private List<Item> items;
}