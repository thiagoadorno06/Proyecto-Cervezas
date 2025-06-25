package com.incade.poo.mozo;

import com.incade.poo.mozo.controller.CervezaController;
import com.incade.poo.mozo.controller.EstadoController;
import com.incade.poo.mozo.controller.MesaController;
import com.incade.poo.mozo.controller.MozoController;
import com.incade.poo.mozo.controller.PedidoController;
import com.incade.poo.mozo.view.Ventana1;

public class Mozo {

    public static void main(String[] args) {
        
        /*
        CervezaController cervezaController = new CervezaController();
        MesaController mesaController = new MesaController();
        MozoController mozoController = new MozoController();
        EstadoController estadoController = new EstadoController();
        
        cervezaController.create("Miller Golden", 6000D, "Suave y refrescante, con un dorado brillante que invita al primer trago");
        cervezaController.create("Inferno Golden", 3500D, "Ligera pero intensa, una golden ale con carácter ardiente");
        cervezaController.create("Fuego Rojo", 2700D, "Cerveza roja con cuerpo y alma, perfecta para quienes buscan sabor profundo");
        cervezaController.create("Llama IPA", 3000D, "Aromática y rebelde, esta IPA enciende el paladar con su amargor equilibrado");
        cervezaController.create("Oscura del Infierno", 4900D, "Densa, tostada y misteriosa; una stout que desafía los sentidos");
        
        mesaController.create(1);
        mesaController.create(2);
        mesaController.create(3);
        mesaController.create(4);
        mesaController.create(5);
        mesaController.create(6);
        
        mozoController.create("Matías", "matiasb@gmail.com", "1234");
        mozoController.create("Thiago", "thiagoadorno@gmail.com", "1234");
        mozoController.create("Laura", "laurasad@gmail.com", "1234");
        mozoController.create("Emilia", "mernesemilia@gmail.com", "1234");
        
        estadoController.create("PENDIENTE"); //Cuando se crea un pedido
        estadoController.create("PROCESANDO");//Cuando un mozo toma el pedido
        estadoController.create("ENTREGADO"); //Cuando el pedido es entregado en la mesa
        estadoController.create("CANCELADO"); //Cuando el cliente o el mozo cancelan el pedido
        estadoController.create("SIN STOCK"); //Cuando no hay stock
        estadoController.create("PAGADO");    //Cuando el pedido fue pagado ya sea por efectivo o transferencia
        
        PedidoController pedidoController = new PedidoController();
        pedidoController.create(1, "Inferno Golden", 2);
        */
        
        //PedidoController pedidoController = new PedidoController();
        //System.out.print(pedidoController.getAll().toString());
                
        Ventana1 v1 = new Ventana1();
            v1.setVisible(true);
            v1.setLocationRelativeTo(null);
    }
}
