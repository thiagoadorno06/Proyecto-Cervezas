package com.incade.poo.mozo;

import com.incade.poo.mozo.controller.CervezaController;
import com.incade.poo.mozo.view.Ventana1;

public class Mozo {

    public static void main(String[] args) {
        CervezaController cervezaController = new CervezaController();
        //cervezaController.create("Miller Golden", 7000D, "La Golden de miller LLoren kukas");
                
        Ventana1 v1 = new Ventana1();
            v1.setVisible(true);
            v1.setLocationRelativeTo(null);
    }
}
