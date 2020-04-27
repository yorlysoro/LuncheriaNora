/*
 * Copyright 2019 yorlysoro <yorlysoro@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package luncheria;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author giovanni
 */
public class ConsultaFactura extends javax.swing.JDialog {
    private basedatos bd;
    private factura fact;
    private DefaultTableModel tabla ; 
    private String nomb_user;
    private String cod_user;
    /**
     * Creates new form ConsultaServicio
     */
    public ConsultaFactura(java.awt.Frame parent, boolean modal, basedatos bd, factura fact, String nomb_user, String cod_user) throws SQLException {
         super(parent, modal);
         BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Iniciando la ventana Consultar Factura");

        this.bd = bd;
        this.fact = fact;
       
        this.nomb_user = nomb_user;
        this.cod_user = cod_user;
        initComponents();
        this.setLocationRelativeTo(null);
        this.tabla =(DefaultTableModel) tablaClientes.getModel();
         this.comboSeleccione.setVisible(false);
        this.lblMostrar.setVisible(false);
        if(!this.cod_user.equalsIgnoreCase("1")){
            this.Cargar_Tabla();
        } else{
            this.comboSeleccione.setVisible(true);
            this.lblMostrar.setVisible(true);
            this.cargarSegunCombo(cod_user);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        JBtn_aceptar = new javax.swing.JButton();
        JBtn_salir = new javax.swing.JButton();
        comboSeleccione = new javax.swing.JComboBox<>();
        lblMostrar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        tablaClientes.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cedula", "Nombre Apellido", "Fecha"
            }
        ));
        tablaClientes.setGridColor(new java.awt.Color(51, 51, 255));
        tablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaClientes);

        JBtn_aceptar.setBackground(new java.awt.Color(51, 102, 255));
        JBtn_aceptar.setFont(new java.awt.Font("MOAM91", 1, 14)); // NOI18N
        JBtn_aceptar.setForeground(new java.awt.Color(255, 255, 255));
        JBtn_aceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/274-checkmark2hover.png"))); // NOI18N
        JBtn_aceptar.setText("ACEPTAR");
        JBtn_aceptar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), java.awt.Color.white, java.awt.Color.white));
        JBtn_aceptar.setEnabled(false);
        JBtn_aceptar.setFocusPainted(false);
        JBtn_aceptar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/274-checkmark2.png"))); // NOI18N
        JBtn_aceptar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/274-checkmark2.png"))); // NOI18N
        JBtn_aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JBtn_aceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JBtn_aceptarMouseExited(evt);
            }
        });
        JBtn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtn_aceptarActionPerformed(evt);
            }
        });

        JBtn_salir.setBackground(new java.awt.Color(51, 102, 255));
        JBtn_salir.setFont(new java.awt.Font("MOAM91", 1, 14)); // NOI18N
        JBtn_salir.setForeground(new java.awt.Color(255, 255, 255));
        JBtn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salida.png"))); // NOI18N
        JBtn_salir.setText("     SALIR");
        JBtn_salir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 0, 0), new java.awt.Color(0, 0, 0), java.awt.Color.white, java.awt.Color.white));
        JBtn_salir.setFocusPainted(false);
        JBtn_salir.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/277-exithover.png"))); // NOI18N
        JBtn_salir.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/277-exithover.png"))); // NOI18N
        JBtn_salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JBtn_salirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JBtn_salirMouseExited(evt);
            }
        });
        JBtn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBtn_salirActionPerformed(evt);
            }
        });

        comboSeleccione.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "ACTIVOS", "INACTIVOS" }));
        comboSeleccione.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboSeleccioneItemStateChanged(evt);
            }
        });

        lblMostrar.setText("Mostrar:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(JBtn_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSeleccione, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(lblMostrar)))
                        .addGap(69, 69, 69)
                        .addComponent(JBtn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JBtn_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JBtn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblMostrar)
                        .addGap(4, 4, 4)
                        .addComponent(comboSeleccione, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JBtn_aceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBtn_aceptarMouseEntered
        JBtn_aceptar.setBackground( new Color(255,255,255));
        JBtn_aceptar.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_JBtn_aceptarMouseEntered

    private void JBtn_aceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBtn_aceptarMouseExited
        JBtn_aceptar.setBackground( new Color(51,102,255));
        JBtn_aceptar.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_JBtn_aceptarMouseExited

    private void JBtn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtn_aceptarActionPerformed
        this.aceptar();
    }//GEN-LAST:event_JBtn_aceptarActionPerformed

    private void JBtn_salirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBtn_salirMouseEntered
        // TODO add your handling code here:
        JBtn_salir.setBackground( new Color(255,255,255));
        JBtn_salir.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_JBtn_salirMouseEntered

    private void JBtn_salirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JBtn_salirMouseExited
        // TODO add your handling code here:
        JBtn_salir.setBackground( new Color(51,102,255));
        JBtn_salir.setForeground(new Color(255,255,255));
    }//GEN-LAST:event_JBtn_salirMouseExited

    private void JBtn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBtn_salirActionPerformed
        BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Saliendo de la ventana Consultar Factura");

        this.dispose();
    }//GEN-LAST:event_JBtn_salirActionPerformed

    private void tablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaClientesMouseClicked
        // TODO add your handling code here:
        this.JBtn_aceptar.setEnabled(true);
    }//GEN-LAST:event_tablaClientesMouseClicked

    private void comboSeleccioneItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboSeleccioneItemStateChanged
        // TODO add your handling code here:
       BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Saliendo de la ventana consultar factura");

        this.cargarSegunCombo(cod_user);
    }//GEN-LAST:event_comboSeleccioneItemStateChanged

    /**
     * @param args the command line arguments
     */
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBtn_aceptar;
    private javax.swing.JButton JBtn_salir;
    private javax.swing.JComboBox<String> comboSeleccione;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMostrar;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables

    private void Cargar_Tabla() throws SQLException {
        
        
        try{
            this.bd.Conectar();
            this.bd.setResult("SELECT factura.cod_factura,factura.ced_cliente,clientes.nombres,clientes.apellidos, factura.fecha FROM factura, clientes WHERE clientes.cedula = factura.ced_cliente and factura.status = '1'");
            String Datos[] = new String[4];
            ResultSet result = this.bd.getResult();
            while(result.next()){
                Datos[0] = (""+result.getString(1));
                Datos[1] = (""+result.getString(2));
                Datos[2] = (""+result.getString(3) + " " +result.getString(4));
                Datos[3] = (""+result.getString(5));
                this.tabla.addRow(Datos);
            }
            //this.bd.Close();
        }catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al cargar la tabla desde la base de datos:{0}", ex.getMessage());
            }
       
    }

    private void aceptar() {
 if(this.tablaClientes.getSelectedRow() >= 0){
            this.fact.setCod_f(String.valueOf(this.tabla.getValueAt(this.tablaClientes.getSelectedRow(), 0)));
            this.fact.setCed(String.valueOf(this.tabla.getValueAt(this.tablaClientes.getSelectedRow(), 1)));
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "Seleccione una fila!!", "Error", JOptionPane.ERROR_MESSAGE);
            BitacoraVentanas.LOG_RAIZ.log(Level.WARNING, "Error al enviar datos a ventana padre");

        }    
    }

    private void cargarSegunCombo(String cod_user) {
        if(cod_user.equalsIgnoreCase("1")){
            if(String.valueOf(this.comboSeleccione.getSelectedItem()).equalsIgnoreCase("TODOS")){
                this.borrarTabla();
                try{
            this.bd.Conectar();
            this.bd.setResult("SELECT factura.cod_factura,factura.ced_cliente,clientes.nombres,clientes.apellidos, factura.fecha FROM factura, clientes WHERE clientes.cedula = factura.ced_cliente");
            String Datos[] = new String[4];
            ResultSet result = this.bd.getResult();
            while(result.next()){
                Datos[0] = (""+result.getString(1));
                Datos[1] = (""+result.getString(2));
                Datos[2] = (""+result.getString(3) + " " +result.getString(4));
                Datos[3] = (""+result.getString(5));
                this.tabla.addRow(Datos);
            }
            //this.bd.Close();
        }catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al cargar la tabla desde la base de datos:{0}", ex.getMessage());
            }
            } else if(String.valueOf(this.comboSeleccione.getSelectedItem()).equalsIgnoreCase("ACTIVOS")){
                this.borrarTabla();
                try{
            this.bd.Conectar();
            this.bd.setResult("SELECT factura.cod_factura,factura.ced_cliente,clientes.nombres,clientes.apellidos, factura.fecha FROM factura, clientes WHERE clientes.cedula = factura.ced_cliente and factura.status = '1'");
            String Datos[] = new String[4];
            ResultSet result = this.bd.getResult();
            while(result.next()){
                Datos[0] = (""+result.getString(1));
                Datos[1] = (""+result.getString(2));
                Datos[2] = (""+result.getString(3) + " " +result.getString(4));
                Datos[3] = (""+result.getString(5));
                this.tabla.addRow(Datos);
            }
            //this.bd.Close();
        }catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al cargar la tabla desde la base de datos:{0}", ex.getMessage());
            }
            }  else if(String.valueOf(this.comboSeleccione.getSelectedItem()).equalsIgnoreCase("INACTIVOS")){
                this.borrarTabla();
              
                try{
            this.bd.Conectar();
            this.bd.setResult("SELECT factura.cod_factura,factura.ced_cliente,clientes.nombres,clientes.apellidos, factura.fecha FROM factura, clientes WHERE clientes.cedula = factura.ced_cliente and factura.status = '0'");
            String Datos[] = new String[4];
            ResultSet result = this.bd.getResult();
            while(result.next()){
                Datos[0] = (""+result.getString(1));
                Datos[1] = (""+result.getString(2));
                Datos[2] = (""+result.getString(3) + " " +result.getString(4));
                Datos[3] = (""+result.getString(5));
                this.tabla.addRow(Datos);
            }
            //this.bd.Close();
        }catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al cargar la tabla desde la base de datos:{0}", ex.getMessage());
            }
          }
        }
    }

    private void borrarTabla() {
        for(int i = 0; i < this.tabla.getRowCount(); i++)
            this.tabla.removeRow(i);
    }
}
