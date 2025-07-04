
package com.incade.poo.mozo.view;

import com.incade.poo.mozo.controller.PedidoController;
import com.incade.poo.mozo.dto.MozoDto;
import com.incade.poo.mozo.dto.PedidoDto;
import java.util.List;

/**
 *
 * @author pc
 */
public class TomarPedido extends javax.swing.JFrame {
    private LoginMozo v9;
    private PedidoDto pedido;
    private List<PedidoDto> pedidos;
    private int indiceActual = 0;
    private MozoDto mozo;
  
    /**
     * Creates new form Ventana10
     */
    public TomarPedido(MozoDto mozo) {
        initComponents();
        setLocationRelativeTo(null);
        this.mozo = mozo;
       PedidoController pedidoController = new PedidoController();
       this.pedidos = pedidoController.getAll();
       if (pedidos.size() > 0) {
        indiceActual = 0;
        mostrarPedidoActual();
    } else {
        txtMesa.setText("-");
        txtCerveza.setText("-");
        txtCantidad.setText("-");
        txtTomarPedido.setEnabled(false);
    } 
   }     
       
    private void mostrarPedidoActual() {
    if (pedidos == null) return;

    pedido = pedidos.get(indiceActual);
    txtMesa.setText(pedido.mesa().toString());
    txtCerveza.setText(pedido.items().get(0).cerveza().nombre());
    txtCantidad.setText(String.valueOf(pedido.items().get(0).cantidad()));
    txtImporte.setText(String.valueOf(pedido.items().get(0).importe()));
    jTextField1.setText((indiceActual + 1) + " / " + pedidos.size());
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTomarPedido = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        txtMesa = new javax.swing.JLabel();
        txtCerveza = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JLabel();
        btnVolver1 = new javax.swing.JToggleButton();
        btnPaginaAnterior = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        btnPaginaSiguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(19, 16, 9));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo Cerveceria 4.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(239, 112, 28));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cantidad:");

        jLabel1.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cerveza:");

        txtTomarPedido.setBackground(new java.awt.Color(0, 0, 0));
        txtTomarPedido.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        txtTomarPedido.setForeground(new java.awt.Color(255, 255, 255));
        txtTomarPedido.setText("Tomar Pedido");
        txtTomarPedido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtTomarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTomarPedidoActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mesa:");

        txtMesa.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        txtMesa.setForeground(new java.awt.Color(255, 255, 255));
        txtMesa.setText("jLabel2");

        txtCerveza.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        txtCerveza.setForeground(new java.awt.Color(255, 255, 255));
        txtCerveza.setText("jLabel6");

        txtCantidad.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(255, 255, 255));
        txtCantidad.setText("jLabel7");

        jLabel2.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Importe:");

        txtImporte.setFont(new java.awt.Font("Roboto Cn", 0, 24)); // NOI18N
        txtImporte.setForeground(new java.awt.Color(255, 255, 255));
        txtImporte.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTomarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel2)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(txtImporte))
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCerveza, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtImporte))
                .addGap(18, 18, 18)
                .addComponent(txtTomarPedido)
                .addGap(24, 24, 24))
        );

        btnVolver1.setBackground(new java.awt.Color(239, 112, 28));
        btnVolver1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        btnVolver1.setText("←");
        btnVolver1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnVolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver1ActionPerformed(evt);
            }
        });

        btnPaginaAnterior.setBackground(new java.awt.Color(238, 112, 28));
        btnPaginaAnterior.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnPaginaAnterior.setForeground(new java.awt.Color(255, 255, 255));
        btnPaginaAnterior.setText("←");
        btnPaginaAnterior.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPaginaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaginaAnteriorActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");

        btnPaginaSiguiente.setBackground(new java.awt.Color(238, 112, 28));
        btnPaginaSiguiente.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnPaginaSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        btnPaginaSiguiente.setText("→");
        btnPaginaSiguiente.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPaginaSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaginaSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(btnVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnPaginaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnPaginaSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPaginaAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPaginaSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setv9(LoginMozo v9)  {
        
        this.v9 = v9;
    }
    private void txtTomarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTomarPedidoActionPerformed
        
        PedidoController pedidoController = new PedidoController();
        pedidoController.updateEstado(pedido.id(), "PROCESANDO");
        pedidoController.takePedido(mozo.email(), pedido.id());
      
        
          
        EstadoPedido v11 =new EstadoPedido();
        v11.setv10(this); 
        v11.setPedido(pedido);
         v11.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_txtTomarPedidoActionPerformed

    private void btnVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver1ActionPerformed

        v9.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVolver1ActionPerformed

    private void btnPaginaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaAnteriorActionPerformed
        
        if (indiceActual > 0) {
            indiceActual--;
            mostrarPedidoActual();
        }
    }//GEN-LAST:event_btnPaginaAnteriorActionPerformed

    private void btnPaginaSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaginaSiguienteActionPerformed
       if (indiceActual < pedidos.size() - 1) {
            indiceActual++;
            mostrarPedidoActual();
    }//GEN-LAST:event_btnPaginaSiguienteActionPerformed

    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPaginaAnterior;
    private javax.swing.JButton btnPaginaSiguiente;
    private javax.swing.JToggleButton btnVolver1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel txtCantidad;
    private javax.swing.JLabel txtCerveza;
    private javax.swing.JLabel txtImporte;
    private javax.swing.JLabel txtMesa;
    private javax.swing.JToggleButton txtTomarPedido;
    // End of variables declaration//GEN-END:variables
}
