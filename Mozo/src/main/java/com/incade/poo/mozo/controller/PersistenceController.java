package com.incade.poo.mozo.controller;

import com.incade.poo.mozo.repository.CervezaJpaController;
import com.incade.poo.mozo.repository.EstadoJpaController;
import com.incade.poo.mozo.repository.ItemJpaController;
import com.incade.poo.mozo.repository.MesaJpaController;
import com.incade.poo.mozo.repository.MozoJpaController;
import com.incade.poo.mozo.repository.PedidoJpaController;

public class PersistenceController {
    CervezaJpaController cervezaJpaController = new CervezaJpaController();
    EstadoJpaController estadoJpaController = new EstadoJpaController();
    ItemJpaController itemJpaController = new ItemJpaController();
    MesaJpaController mesaJpaController = new MesaJpaController();
    MozoJpaController mozoJpaController = new MozoJpaController();
    PedidoJpaController pedidoJpaController = new PedidoJpaController();
}
