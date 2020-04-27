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


import BDA.Encriptacion;
import BDA.OlvidoContrasena;
import VALIDACION.Validacion;
import VALIDACION.limitar;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.logging.Level;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;


public class New_A extends javax.swing.JFrame {
        static String cajero;
        int x;
        int y;
        public boolean mostrar = true;
        private javax.swing.JTextField[] lcampos = new javax.swing.JTextField[2];
        private VALIDACION.Validacion vadid = new Validacion();
        private int conta_interno=0;
        private basedatos bd = new basedatos();
    public New_A() {
        BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "INICIANDO EL LOGGIN");
       initComponents();
       //adaptar imagen de presentacion 
      textpassvisible.setVisible(false);
      textpass.setVisible(true);
       
      ImageIcon imagpres = new ImageIcon(getClass().getResource("/imagenes/prese.jpg"));
      Icon fondo1=new ImageIcon(imagpres.getImage().getScaledInstance(img_presen.getWidth(),img_presen.getHeight(), Image.SCALE_DEFAULT));
      img_presen.setIcon(fondo1);
      this.repaint();
       
      lcampos[0] = this.textusuario;
      lcampos[1] = this.textpass;
      this.textusuario.requestFocus();
      this.setLocationRelativeTo(null);
      
    }

   
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        Barra = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        btnMinimizar = new javax.swing.JButton();
        icon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        img_presen = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textusuario = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        textpass = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        textpassvisible = new javax.swing.JTextField();
        OlvidoContrasena = new javax.swing.JLabel();

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesion");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setMinimumSize(new java.awt.Dimension(800, 390));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        Barra.setBackground(new java.awt.Color(0, 0, 0));
        Barra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                BarraMouseDragged(evt);
            }
        });
        Barra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                BarraMousePressed(evt);
            }
        });
        Barra.setLayout(null);

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Cancel_20px_5.png"))); // NOI18N
        btnCerrar.setBorder(null);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Cancel_20px_7.png"))); // NOI18N
        btnCerrar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_Cancel_20px_7.png"))); // NOI18N
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        Barra.add(btnCerrar);
        btnCerrar.setBounds(760, 0, 40, 30);

        btnMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Minus_20px_4.png"))); // NOI18N
        btnMinimizar.setBorderPainted(false);
        btnMinimizar.setContentAreaFilled(false);
        btnMinimizar.setFocusPainted(false);
        btnMinimizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Minus_20px_6.png"))); // NOI18N
        btnMinimizar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Minus_20px_6.png"))); // NOI18N
        btnMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizarActionPerformed(evt);
            }
        });
        Barra.add(btnMinimizar);
        btnMinimizar.setBounds(730, 0, 52, 30);

        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarionew.png"))); // NOI18N
        Barra.add(icon);
        icon.setBounds(0, 0, 30, 30);

        jLabel3.setFont(new java.awt.Font("Malgun Gothic", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Inicio de Sesion");
        Barra.add(jLabel3);
        jLabel3.setBounds(30, 0, 160, 30);

        getContentPane().add(Barra);
        Barra.setBounds(0, 0, 800, 30);

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));
        jPanel2.setMinimumSize(new java.awt.Dimension(350, 480));
        jPanel2.setLayout(null);

        img_presen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        img_presen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/prese.jpg"))); // NOI18N
        jPanel2.add(img_presen);
        img_presen.setBounds(0, 30, 470, 360);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 470, 390);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(350, 480));
        jPanel1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Malgun Gothic", 3, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Iniciar Sesion");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(50, 60, 240, 40);

        jLabel6.setFont(new java.awt.Font("Malgun Gothic", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Usuario");
        jLabel6.setToolTipText("");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 130, 80, 20);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Contacts_32px_2 - copia.png"))); // NOI18N
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 170, 32, 30);

        textusuario.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        textusuario.setBorder(null);
        textusuario.setOpaque(false);
        textusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textusuarioActionPerformed(evt);
            }
        });
        textusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textusuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textusuarioKeyTyped(evt);
            }
        });
        jPanel1.add(textusuario);
        textusuario.setBounds(60, 170, 250, 30);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator2);
        jSeparator2.setBounds(20, 200, 290, 2);

        jLabel5.setFont(new java.awt.Font("Malgun Gothic", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Contraseña");
        jLabel5.setToolTipText("");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 210, 110, 20);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(20, 270, 290, 2);

        textpass.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        textpass.setBorder(null);
        textpass.setOpaque(false);
        textpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textpassActionPerformed(evt);
            }
        });
        textpass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textpassKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textpassKeyTyped(evt);
            }
        });
        jPanel1.add(textpass);
        textpass.setBounds(60, 240, 220, 30);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8_Password_32px - copia.png"))); // NOI18N
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 240, 34, 30);

        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btnini.png"))); // NOI18N
        btnIniciar.setBorderPainted(false);
        btnIniciar.setContentAreaFilled(false);
        btnIniciar.setFocusPainted(false);
        btnIniciar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/btninihover.png"))); // NOI18N
        btnIniciar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btninihover.png"))); // NOI18N
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        jPanel1.add(btnIniciar);
        btnIniciar.setBounds(70, 310, 190, 70);

        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/207-eye.png"))); // NOI18N
        btnMostrar.setBorder(null);
        btnMostrar.setBorderPainted(false);
        btnMostrar.setContentAreaFilled(false);
        btnMostrar.setDefaultCapable(false);
        btnMostrar.setFocusPainted(false);
        btnMostrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/210-eye-blocked.png"))); // NOI18N
        btnMostrar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/210-eye-blocked.png"))); // NOI18N
        btnMostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMostrarMouseClicked(evt);
            }
        });
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        btnMostrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnMostrarKeyPressed(evt);
            }
        });
        jPanel1.add(btnMostrar);
        btnMostrar.setBounds(280, 240, 30, 30);

        textpassvisible.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        textpassvisible.setBorder(null);
        textpassvisible.setOpaque(false);
        textpassvisible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textpassvisibleActionPerformed(evt);
            }
        });
        jPanel1.add(textpassvisible);
        textpassvisible.setBounds(60, 240, 220, 30);

        OlvidoContrasena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OlvidoContrasena.setText("¿Olvido su contraseña?");
        OlvidoContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OlvidoContrasenaMouseClicked(evt);
            }
        });
        jPanel1.add(OlvidoContrasena);
        OlvidoContrasena.setBounds(54, 290, 210, 14);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(470, 0, 330, 390);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        int mj= JOptionPane.YES_NO_OPTION;
        int mensaje= JOptionPane.showConfirmDialog(this, "¿Realmente desea salir?","Salir",mj);
        if (mensaje == 0) {
           BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Saliendo del Sistema");

            System.exit(0);
            this.setState(New_A.ICONIFIED);
            
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizarActionPerformed
        this.setState(New_A.ICONIFIED);
    }//GEN-LAST:event_btnMinimizarActionPerformed

    private void BarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraMouseDragged
        int a = evt.getXOnScreen();
        int b = evt.getYOnScreen();
        this.setLocation(a - x,b - y);
    }//GEN-LAST:event_BarraMouseDragged

    private void BarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BarraMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_BarraMousePressed

    private void textpassvisibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textpassvisibleActionPerformed

    }//GEN-LAST:event_textpassvisibleActionPerformed

    private void btnMostrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMostrarKeyPressed
      
    }//GEN-LAST:event_btnMostrarKeyPressed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed

        if(mostrar){

            textpassvisible.setVisible(true);
            textpass.setVisible(false);
            textpassvisible.setText(textpass.getText());
            mostrar=false;

        }else{
            textpassvisible.setVisible(false);
            textpass.setVisible(true);
            textpass.setText(textpassvisible.getText());
            mostrar=true;
        }

    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // Se realiza el llamado al metodo entrar
        entrar();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void textpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textpassActionPerformed

    private void textusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textusuarioActionPerformed

    private void textusuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textusuarioKeyPressed
      if(evt.getKeyCode() == evt.VK_ENTER)
            this.textpass.requestFocus();
       
        
        
    }//GEN-LAST:event_textusuarioKeyPressed

    private void textpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textpassKeyPressed
         if(evt.getKeyCode() == evt.VK_ENTER && this.vadid.campos(lcampos))
            this.entrar();
        
    }//GEN-LAST:event_textpassKeyPressed

    private void btnMostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarMouseClicked
        
    }//GEN-LAST:event_btnMostrarMouseClicked

    private void textusuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textusuarioKeyTyped
        // TODO add your handling code here:
        if(textusuario.getText().length() == limitar.nomapell){
            evt.consume();
        }
    }//GEN-LAST:event_textusuarioKeyTyped

    private void textpassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textpassKeyTyped
        // TODO add your handling code here:
        if(textpass.getText().length() == limitar.fulltext){
            evt.consume();
        }
    }//GEN-LAST:event_textpassKeyTyped

    private void OlvidoContrasenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OlvidoContrasenaMouseClicked
        // TODO add your handling code here:
        new OlvidoContrasena(this.bd).setVisible(true);
    }//GEN-LAST:event_OlvidoContrasenaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Barra;
    private javax.swing.JLabel OlvidoContrasena;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnMinimizar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel img_presen;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField textpass;
    private javax.swing.JTextField textpassvisible;
    private javax.swing.JTextField textusuario;
    // End of variables declaration//GEN-END:variables

    // METODO DE ENTRADA DONDE SE COMPARAN SI LOS DATOS INTRODUCIDOS POR EL USUARIO EXISTEN EN LA BASE DE DATOS
    private void entrar() {
 try{
       
        
        this.conta_interno++;
        if(this.conta_interno==3){
            JOptionPane.showMessageDialog(this, "A superado el numero de intentos!!!", "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
      String usuario,status="1";
      usuario=textusuario.getText();
      
      
      this.bd.Conectar();
    
  // VALIDAR QUE EL CUADRO DE TEXTO "NOMBRE" NO ESTE VACIO
    if(textusuario.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESE NOMBRE DE USUARIO","ERROR",JOptionPane.WARNING_MESSAGE);
       textusuario.requestFocus();
       BitacoraVentanas.LOG_RAIZ.log(Level.WARNING, "Error al introducir el usuario");
    }
     else
    {
  // VALIDAR QUE EL CUADRO DE TEXTO "CLAVE" NO ESTE VACIO

    if(textpass.getPassword().equals(""))
        
    {
        
        JOptionPane.showMessageDialog(this,"INGRESE CLAVE","ERROR",JOptionPane.WARNING_MESSAGE);
        textpass.requestFocus();  
        BitacoraVentanas.LOG_RAIZ.log(Level.WARNING, "Error al introducir la contraseña");
    }
    
    else{

//CONSULTA SQL PARA VALIDAR QUE EL USUARIO REALMENTE EXISTE
        
        bd.setResult("SELECT cedula_empleado,nombre_usuario,contrasena,cod_nivel,status FROM usuarios  WHERE nombre_usuario='"+textusuario.getText()+"'and  contrasena='"+textpass.getText()+"'");
        ResultSet result = bd.getResult();
  
       
        if(result.next()) {
            
        
        cajero=(""+result.getString("nombre_usuario"));
        String ced = (""+result.getString("cedula_empleado"));
        String cod_nivel=(""+result.getString("cod_nivel"));
        status = (""+result.getString("status"));
        String Contrasena = Encriptacion.Desencriptar(""+result.getString("contrasena"));
        
        
        if(status == "0"){
            JOptionPane.showMessageDialog(this,"El usuario esta desactivado","Error", JOptionPane.ERROR_MESSAGE);
            Limpiar();
            BitacoraVentanas.LOG_RAIZ.log(Level.SEVERE, "El usuario esta descativado");
        }else {
        new New_menu(cajero, cod_nivel, bd, ced).setVisible(true);
        this.setLocationRelativeTo(null);
        this.dispose(); 
        }
        }          
            
        else
        {
            JOptionPane.showMessageDialog(this,"USUARIO O CLAVE INCORRECTO");
            Limpiar();
            BitacoraVentanas.LOG_RAIZ.log(Level.SEVERE, "Intento de inicio de session fallido");
         
            }    
        }
    }
       }catch(Exception e){
        JOptionPane.showMessageDialog(this,"No hay conexion" + e.getMessage());
        BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error de conexion con la base de datos: {0}", e.getMessage());
      
        }    
    }
   
        private void Limpiar() {
 
            textusuario.setText("");     
            textpass.setText(""); 
            textpassvisible.setText("");
    }
    
}
