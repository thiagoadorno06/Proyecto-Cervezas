package com.incade.poo.mozo;

import com.incade.poo.mozo.repository.PersistenceController;
import com.incade.poo.mozo.view.Ventana1;

public class Mozo {

    public static void main(String[] args) {
        PersistenceController persistenceController = new PersistenceController();
        Ventana1 v1 = new Ventana1();
            v1.setVisible(true);
            v1.setLocationRelativeTo(null);
    }
}
