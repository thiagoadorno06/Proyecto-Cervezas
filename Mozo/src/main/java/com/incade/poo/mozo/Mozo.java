package com.incade.poo.mozo;

import com.incade.poo.mozo.controller.CervezaController;
import com.incade.poo.mozo.view.Ventana1;

public class Mozo {

    public static void main(String[] args) {
        CervezaController cervezaController = new CervezaController();
        //cervezaController.create("Miller Golden", 7000D, "La Golden de miller LLoren kukas");
         cervezaController.create("Inferno Golden", 1500.00, "");
         cervezaController.create("Fuego Rojo", 1700.00, "aaa");
         cervezaController.create("Llama IPA", 2000.00, "aaa");
         cervezaController.create("Oscura del Infierno", 1900.00, "aaa");
                
        Ventana1 v1 = new Ventana1();
            v1.setVisible(true);
            v1.setLocationRelativeTo(null);
    }
}
