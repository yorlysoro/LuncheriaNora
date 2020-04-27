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

import VALIDACION.Validacion;
import VALIDACION.limitar;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class servicio extends javax.swing.JFrame {

  private basedatos bd;
    private String cod;
    private Calendar Cal;
    private String fec;
    private String nomb_user;
    private String cod_user;
    
    private javax.swing.JTextField[] lcampos = new javax.swing.JTextField[4];
     private javax.swing.JComboBox[] lcombox = new javax.swing.JComboBox[1];
     private VALIDACION.Validacion vadid = new Validacion();
   
    public servicio(basedatos bd, String nomb_user, String cod_user) {
        initComponents();
        this.setLocationRelativeTo(null);
        BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Iniciando la ventana Servicio");

        
        ImageIcon caldr = new ImageIcon(getClass().getResource("/imagenes/calendar.png"));
        Icon fondo1=new ImageIcon(caldr.getImage().getScaledInstance(calendar1.getWidth(),calendar1.getHeight(), Image.SCALE_DEFAULT));
        calendar1.setIcon(fondo1);
        
        ImageIcon carrito = new ImageIcon(getClass().getResource("/imagenes/059-cart.png"));
        Icon fondo2=new ImageIcon(carrito.getImage().getScaledInstance(icocarr.getWidth(),icocarr.getHeight(), Image.SCALE_DEFAULT));
        icocarr.setIcon(fondo2);
        
        this.Cal = Calendar.getInstance();
        this.fec =Cal.get(Cal.DATE)+"/"+(Cal.get(Cal.MONTH)+1)+"/"+ Cal.get(Cal.YEAR);
        this.bd = bd;
        this.cod_user = cod_user;
        this.nomb_user = nomb_user;
         this.textFecha.setText(fec);
         
         //lcampos[0] = (JTextField) textCodigo;
         lcampos[0] = textNombre;
         lcampos[1] = textDescripcion1;
         lcampos[2] = textPrecio;
         lcampos[3] = textCantidad;
         
         lcombox[0] = CbxCategoria;
    //VALIDAR TEXTO DE LOS JTEXTFIEL "CASILLAS DE TEXTO"
       
        SLetras(textNombre);
        
        
        SNumeros(textCantidad);
        SNumeros(textPrecio);
        String codigo = String.valueOf(ThreadLocalRandom.current().nextInt(1, 600000 + 1));
        this.textCodigo.setText(codigo);
        
        this.cargarCombo();
        this.verificarUsuario(cod_user);
   // SE REALIZA EL LLAMADO HA EL METODO LIMPIAR
       limpiar();
    }
     public void setCod(String cod) {
        this.cod = cod;
        try{
            this.bd.Conectar();
            this.bd.setResult("SELECT * FROM productos WHERE  codigo='" + this.cod + "'");
            ResultSet result = this.bd.getResult();
            String categoria = "";
            while(result.next()){
                this.textCodigo.setText(""+result.getString(1));
                this.textNombre.setText(""+result.getString(2));
                this.textDescripcion1.setText(""+result.getString(3));
                this.textPrecio.setText(""+result.getString(4));
                categoria = result.getString(5);
                this.textCantidad.setText(""+result.getString(6));
                this.textStatus.setText(""+result.getString(7));
            }
            switch(categoria){
                case "1" : this.CbxCategoria.setSelectedItem("Comida"); break;
                case "2" : this.CbxCategoria.setSelectedItem("Bebidas"); break;
                case "3" : this.CbxCategoria.setSelectedItem("Golosinas"); break;
            }
            this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
            //this.bd.Close();
        } catch (SQLException ex){
             BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al cargar datos desde la base de datos:{0}", ex.getMessage());

        }
    }
  //METODO PARA VALIDAR QUE LA ENTRADA DE DATOS A LAS CASILLAS DE TEXTOS SEA SOLO NUMEROS   
    public void SNumeros (JTextField a)
    {
        a.addKeyListener(new KeyAdapter()
        {
            public void keyTyped( KeyEvent e)
            {
                char c=e. getKeyChar();
                if(!Character.isDigit(c))
                {
                    getToolkit().beep();;
                    e.consume();
                }
            }
        });
           
    }
     //METODO PARA VALIDAR QUE LA ENTRADA DE DATOS A LAS CASILLAS DE TEXTOS SEA SOLO LETRAS   
  
     public void SLetras (JTextField a)
    {
        a.addKeyListener(new KeyAdapter()
        {
            public void keyTyped( KeyEvent e)
            {
                char c=e. getKeyChar();
                if(!Character.isLetter(c) && !Character.isWhitespace(c))
                {
                    getToolkit().beep();;
                    e.consume();
                }
            }
        });
    }
      
   // SE LIMITA LOS CUADROS DE TEXTOS A UN LIMITE ESPECIFICO DE CARACTERES ATRAVEZ 
    //DE UNA CLASE DE HERNCIA QUE SE ENCUENTRA EN EL PAQUETE DE VALIDACION 
    //LLAMADA LIMITAR
     
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        registrousuario = new javax.swing.JLabel();
        Status = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        textFecha = new javax.swing.JTextField();
        textStatus = new javax.swing.JTextField();
        Codigo = new javax.swing.JLabel();
        Nombre = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        info = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        textNombre = new javax.swing.JTextField();
        textCantidad = new javax.swing.JTextField();
        Descripcion = new javax.swing.JLabel();
        Precio = new javax.swing.JLabel();
        textPrecio = new javax.swing.JTextField();
        CbxCategoria = new javax.swing.JComboBox();
        Categoria = new javax.swing.JLabel();
        icocarr = new javax.swing.JLabel();
        categoria = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        calendar1 = new javax.swing.JLabel();
        calendar4 = new javax.swing.JLabel();
        calendar5 = new javax.swing.JLabel();
        guardar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textDescripcion1 = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        consultar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        textCodigo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Productos");
        setIconImages(null);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(580, 530));
        setPreferredSize(new java.awt.Dimension(710, 530));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(600, 570));
        jPanel1.setPreferredSize(new java.awt.Dimension(400, 570));
        jPanel1.setLayout(null);

        registrousuario.setFont(new java.awt.Font("Malgun Gothic", 3, 24)); // NOI18N
        registrousuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registrousuario.setText("Registro de Productos");
        jPanel1.add(registrousuario);
        registrousuario.setBounds(10, 10, 280, 33);

        Status.setBackground(new java.awt.Color(255, 255, 255));
        Status.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        Status.setForeground(new java.awt.Color(255, 255, 255));
        Status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Status.setText("Status");
        jPanel1.add(Status);
        Status.setBounds(640, 10, 41, 20);

        Fecha.setBackground(new java.awt.Color(255, 255, 255));
        Fecha.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        Fecha.setForeground(new java.awt.Color(255, 255, 255));
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("Fecha");
        jPanel1.add(Fecha);
        Fecha.setBounds(510, 10, 70, 20);

        textFecha.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textFecha.setForeground(new java.awt.Color(255, 255, 153));
        textFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFecha.setBorder(null);
        textFecha.setEnabled(false);
        textFecha.setOpaque(false);
        textFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFechaActionPerformed(evt);
            }
        });
        jPanel1.add(textFecha);
        textFecha.setBounds(510, 30, 80, 20);

        textStatus.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textStatus.setForeground(new java.awt.Color(255, 255, 153));
        textStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textStatus.setBorder(null);
        textStatus.setEnabled(false);
        textStatus.setOpaque(false);
        textStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textStatusActionPerformed(evt);
            }
        });
        jPanel1.add(textStatus);
        textStatus.setBounds(640, 30, 40, 20);

        Codigo.setBackground(new java.awt.Color(255, 255, 255));
        Codigo.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        Codigo.setForeground(new java.awt.Color(255, 255, 255));
        Codigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Codigo.setText("Codigo");
        jPanel1.add(Codigo);
        Codigo.setBounds(60, 70, 70, 20);

        Nombre.setBackground(new java.awt.Color(255, 255, 255));
        Nombre.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        Nombre.setForeground(new java.awt.Color(255, 255, 255));
        Nombre.setText("Nombre");
        jPanel1.add(Nombre);
        Nombre.setBounds(70, 130, 80, 30);

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4);
        jSeparator4.setBounds(510, 50, 80, 2);

        info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/info.png"))); // NOI18N
        jPanel1.add(info);
        info.setBounds(610, 30, 20, 20);

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator3);
        jSeparator3.setBounds(640, 50, 40, 10);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1);
        jSeparator1.setBounds(70, 120, 180, 10);

        textNombre.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textNombre.setForeground(new java.awt.Color(255, 255, 255));
        textNombre.setBorder(null);
        textNombre.setOpaque(false);
        textNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNombreActionPerformed(evt);
            }
        });
        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNombreKeyTyped(evt);
            }
        });
        jPanel1.add(textNombre);
        textNombre.setBounds(70, 160, 330, 30);

        textCantidad.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textCantidad.setForeground(new java.awt.Color(255, 255, 255));
        textCantidad.setBorder(null);
        textCantidad.setOpaque(false);
        textCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCantidadActionPerformed(evt);
            }
        });
        textCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textCantidadKeyTyped(evt);
            }
        });
        jPanel1.add(textCantidad);
        textCantidad.setBounds(300, 370, 200, 30);

        Descripcion.setBackground(new java.awt.Color(255, 255, 255));
        Descripcion.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        Descripcion.setForeground(new java.awt.Color(255, 255, 255));
        Descripcion.setText("Descripcion");
        jPanel1.add(Descripcion);
        Descripcion.setBounds(70, 200, 120, 30);

        Precio.setBackground(new java.awt.Color(255, 255, 255));
        Precio.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        Precio.setForeground(new java.awt.Color(255, 255, 255));
        Precio.setText("Precio");
        jPanel1.add(Precio);
        Precio.setBounds(70, 270, 70, 30);

        textPrecio.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textPrecio.setForeground(new java.awt.Color(255, 255, 255));
        textPrecio.setBorder(null);
        textPrecio.setOpaque(false);
        textPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPrecioActionPerformed(evt);
            }
        });
        textPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textPrecioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textPrecioKeyTyped(evt);
            }
        });
        jPanel1.add(textPrecio);
        textPrecio.setBounds(70, 300, 120, 30);

        CbxCategoria.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        CbxCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        CbxCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CbxCategoriaItemStateChanged(evt);
            }
        });
        CbxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CbxCategoriaActionPerformed(evt);
            }
        });
        CbxCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CbxCategoriaKeyPressed(evt);
            }
        });
        jPanel1.add(CbxCategoria);
        CbxCategoria.setBounds(70, 370, 190, 30);

        Categoria.setBackground(new java.awt.Color(255, 255, 255));
        Categoria.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        Categoria.setForeground(new java.awt.Color(255, 255, 255));
        Categoria.setText("Categoria");
        jPanel1.add(Categoria);
        Categoria.setBounds(70, 340, 100, 30);

        icocarr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/059-cart.png"))); // NOI18N
        jPanel1.add(icocarr);
        icocarr.setBounds(20, 90, 40, 40);

        categoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/categoria.png"))); // NOI18N
        jPanel1.add(categoria);
        categoria.setBounds(20, 370, 30, 30);

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator6);
        jSeparator6.setBounds(300, 400, 200, 10);

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator7);
        jSeparator7.setBounds(70, 190, 340, 10);

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator8);
        jSeparator8.setBounds(70, 330, 120, 10);

        calendar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendar.png"))); // NOI18N
        jPanel1.add(calendar1);
        calendar1.setBounds(480, 30, 20, 20);

        calendar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/descrip.png"))); // NOI18N
        jPanel1.add(calendar4);
        calendar4.setBounds(20, 220, 30, 40);

        calendar5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bs.png"))); // NOI18N
        jPanel1.add(calendar5);
        calendar5.setBounds(20, 290, 30, 40);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Guardar.png"))); // NOI18N
        guardar.setBorderPainted(false);
        guardar.setContentAreaFilled(false);
        guardar.setEnabled(false);
        guardar.setFocusPainted(false);
        guardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Guardarhover.png"))); // NOI18N
        guardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Guardarhover.png"))); // NOI18N
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel1.add(guardar);
        guardar.setBounds(10, 430, 130, 60);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        eliminar.setBorderPainted(false);
        eliminar.setContentAreaFilled(false);
        eliminar.setEnabled(false);
        eliminar.setFocusPainted(false);
        eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminarhover.png"))); // NOI18N
        eliminar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminarhover.png"))); // NOI18N
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel1.add(eliminar);
        eliminar.setBounds(140, 430, 130, 60);

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificarhover.png"))); // NOI18N
        modificar.setBorderPainted(false);
        modificar.setContentAreaFilled(false);
        modificar.setEnabled(false);
        modificar.setFocusPainted(false);
        modificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        modificar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPanel1.add(modificar);
        modificar.setBounds(270, 430, 130, 60);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nomprod.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 150, 32, 40);

        jLabel2.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cantidad");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(300, 340, 110, 30);

        textDescripcion1.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textDescripcion1.setForeground(new java.awt.Color(255, 255, 255));
        textDescripcion1.setBorder(null);
        textDescripcion1.setOpaque(false);
        textDescripcion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDescripcion1ActionPerformed(evt);
            }
        });
        textDescripcion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textDescripcion1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textDescripcion1KeyTyped(evt);
            }
        });
        jPanel1.add(textDescripcion1);
        textDescripcion1.setBounds(70, 230, 350, 30);

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator9);
        jSeparator9.setBounds(70, 260, 350, 10);

        consultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultar.png"))); // NOI18N
        consultar.setBorderPainted(false);
        consultar.setContentAreaFilled(false);
        consultar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultarhover.png"))); // NOI18N
        consultar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultarhover.png"))); // NOI18N
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });
        jPanel1.add(consultar);
        consultar.setBounds(410, 430, 130, 60);

        btnActivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Btn_activar.png"))); // NOI18N
        btnActivar.setBorderPainted(false);
        btnActivar.setContentAreaFilled(false);
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActivar);
        btnActivar.setBounds(550, 430, 150, 60);

        textCodigo.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textCodigo.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(textCodigo);
        textCodigo.setBounds(70, 94, 180, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 730, 530);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textStatusActionPerformed

    private void textNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNombreActionPerformed

    private void textCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCantidadActionPerformed

    private void textPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPrecioActionPerformed

    private void CbxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CbxCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CbxCategoriaActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
                 eliminar();
    }//GEN-LAST:event_eliminarActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
  // SE REALIZA EL LLAMADO HA EL METODO guardar
        guardar()   ;   
    }//GEN-LAST:event_guardarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
  // SE REALIZA EL LLAMADO HA EL METODO modificar
        modificar();        // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed

    private void textFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFechaActionPerformed

    private void textNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyPressed
      if(evt.getKeyCode() == evt.VK_ENTER)
            this.textDescripcion1.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textNombre.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textNombreKeyPressed

    private void textCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCantidadKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER && this.textCantidad.getText().length() > 0)
            this.guardar();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textCantidad.getText().equalsIgnoreCase("") && this.textCantidad.getText().length() == 0 && this.textCantidad.getText().isEmpty()){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textCantidad.getText().length() > 0 && !this.textCantidad.getText().isEmpty()){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textCantidadKeyPressed

    private void textPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPrecioKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            this.textCantidad.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textPrecio.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textPrecioKeyPressed

    private void CbxCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CbxCategoriaKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           guardar();
       }
    }//GEN-LAST:event_CbxCategoriaKeyPressed

    private void textDescripcion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDescripcion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textDescripcion1ActionPerformed

    private void textDescripcion1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDescripcion1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == evt.VK_ENTER)
            this.textPrecio.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textDescripcion1.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textDescripcion1KeyPressed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
        // SE REALIZA EL LLAMADO HA EL METODO
        consultar();
    }//GEN-LAST:event_consultarActionPerformed

    private void CbxCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CbxCategoriaItemStateChanged
        // TODO add your handling code here:
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || String.valueOf(this.CbxCategoria.getSelectedItem()).equalsIgnoreCase("SELECT")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_CbxCategoriaItemStateChanged

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:
        this.activar();
        
    }//GEN-LAST:event_btnActivarActionPerformed

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped
        // TODO add your handling code here:
        if(textNombre.getText().length() == limitar.nomapell){
            evt.consume();
        }
    }//GEN-LAST:event_textNombreKeyTyped

    private void textDescripcion1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDescripcion1KeyTyped
        // TODO add your handling code here:
        if(textDescripcion1.getText().length() == limitar.fulltext){
            evt.consume();
        }
    }//GEN-LAST:event_textDescripcion1KeyTyped

    private void textPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPrecioKeyTyped
        // TODO add your handling code here:
        if(textPrecio.getText().length() == limitar.precios){
            evt.consume();
        }
    }//GEN-LAST:event_textPrecioKeyTyped

    private void textCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCantidadKeyTyped
        // TODO add your handling code here:
        if(textCantidad.getText().length() == limitar.cant){
            evt.consume();
        }
    }//GEN-LAST:event_textCantidadKeyTyped

    private void textCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCantidadKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode() == evt.VK_ENTER && this.textCantidad.getText().length() > 0)
            this.guardar();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textCantidad.getText().equalsIgnoreCase("") && this.textCantidad.getText().length() == 0 && this.textCantidad.getText().isEmpty()){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textCantidad.getText().length() > 0 && !this.textCantidad.getText().isEmpty()){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textCantidadKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Categoria;
    private javax.swing.JComboBox CbxCategoria;
    private javax.swing.JLabel Codigo;
    private javax.swing.JLabel Descripcion;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Precio;
    private javax.swing.JLabel Status;
    private javax.swing.JButton btnActivar;
    private javax.swing.JLabel calendar1;
    private javax.swing.JLabel calendar4;
    private javax.swing.JLabel calendar5;
    private javax.swing.JLabel categoria;
    private javax.swing.JButton consultar;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel icocarr;
    private javax.swing.JLabel info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton modificar;
    private javax.swing.JLabel registrousuario;
    private javax.swing.JTextField textCantidad;
    private javax.swing.JLabel textCodigo;
    private javax.swing.JTextField textDescripcion1;
    private javax.swing.JTextField textFecha;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textPrecio;
    private javax.swing.JTextField textStatus;
    // End of variables declaration//GEN-END:variables

    
    // METODO QUE ME PERMITE GUARDAR LOS SERVICIOS
 private void guardar() {


//OBTENER FECHA DE FORMA AUTOMATICA

    
String  fechaR  =       fec   ;
String  nombre  =       textNombre.getText()  ;
String  codigo  =       textCodigo.getText()  ;
String  descripcion=    textCantidad.getText()  ;
String  precio    =     textPrecio.getText();
String  categoria =     String.valueOf(CbxCategoria.getSelectedItem());


String  status="1"      ;                      
String sSQL="";
String mensaje ="";       


        // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
    if(textCodigo.getText().equals(""))
    
                {    JOptionPane.showMessageDialog(this,"INGRESAR CODIGO","ERROR",JOptionPane.WARNING_MESSAGE);
     }else   
        // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
             if(textNombre.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR NOMBRE","ERROR",JOptionPane.WARNING_MESSAGE);
    
       
    }
       // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
       else       if(textCantidad.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR DESCRIPCION   ","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
        // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
 
              } else       if(textPrecio.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR PRECIO","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
     // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
   
              } else       if(categoria.equals("SELEC"))
    {
       JOptionPane.showMessageDialog(this,"SELECCIONAR CATEGORIA","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
              }
         
        
       
        else{ 
         
    //MENSAJE DE CONFIRMACION
            String[] opciones = {"ACEPTAR", "CANCELAR"};
            int opcion = JOptionPane.showOptionDialog(
                               null                             //componente
                             , "PRESIONE ACEPTAR PARA GUARDAR LOS DATOS ?"            // Mensaje
                             , "CONFIRMAR"         // Titulo en la barra del cuadro
                             , JOptionPane.DEFAULT_OPTION       // Tipo de opciones
                             , JOptionPane.INFORMATION_MESSAGE  // Tipo de mensaje (icono)
                             , null                             // Icono (ninguno)
                             , opciones                         // Opciones personalizadas
                             , null                             // Opcion por defecto
                           );
              if (opcion == JOptionPane.YES_OPTION)
  {
    

        try{

           this.bd.Conectar();
           String categorias = "";
           
           switch(String.valueOf(this.CbxCategoria.getSelectedItem())){
               case "Comida" : categorias = "1";break;
               case "Bebidas": categorias = "2";break;
               case "Golosinas" : categorias = "3";break;
           }
               
      //CONSULTA SQL QUE ME GUARDA LOS NUEVOS REGISTROS         
     
           this.bd.setStm("INSERT INTO productos(codigo,nombre,Descripcion,Precio, Categorias, Cant, status)" +
                   " VALUES ('" + this.textCodigo.getText() + "', '" +  this.textNombre.getText() + "', '" + this.textDescripcion1.getText() + "', '"
                    + Double.valueOf(this.textPrecio.getText()) + "', '" + categorias +  "', '" + this.textCantidad.getText() + 
                   "', '" + status +"')");
            mensaje = "SE HA GUARDADO DE MANERA CORRECTA";
              JOptionPane.showMessageDialog(null, mensaje);
               BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Servicio guardado correctamente");

                limpiar();    
                //this.bd.Close();
                } catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al guardar en la base de datos:{0}", ex.getMessage());
                JOptionPane.showMessageDialog(this," YA EXISTE UN PRODUCTO CON DICHO CODIGO","ERROR",JOptionPane.WARNING_MESSAGE);
                }
               
    
    
    
    } if (opcion == JOptionPane.NO_OPTION){
                   JOptionPane.showMessageDialog(this,"NO SE GUARDO","ERROR",JOptionPane.WARNING_MESSAGE);  
              }
 
}}
// METODO QUE ME PERMITE VACIAR TODAS LAS CASILLAS DE TEXTOS DE LA INTERFAZ DE SERVICIOS
    private void limpiar() {
    textFecha.setText(fec);    
    textCodigo.setText("")      ; 
    textNombre.setText("")  ;
    textCantidad.setText("")  ;
    textPrecio.setText("");
    textCodigo.setText("")  ;
    textStatus.setText("")  ;
    textDescripcion1.setText("");
    this.CbxCategoria.setSelectedItem("SELECCIONE");

    this.guardar.setEnabled(false);
    this.eliminar.setEnabled(false);
    this.modificar.setEnabled(false);
     String codigo = String.valueOf(ThreadLocalRandom.current().nextInt(1, 600000 + 1));
     this.textCodigo.setText(codigo);
    
    }
    
    //METEDO QUE ME PERMITE UNA ELIMINACION LOGICA DE LOS SERVICIOS
   private void eliminar() {

           String mensaje="";
         
         String[] opciones = {"ACEPTAR", "CANCELAR"};
            int opcion = JOptionPane.showOptionDialog(
                               null                             //componente
                             , "PRESIONE ACEPTAR PARA ELIMINAR ?"            // Mensaje
                             , "CONFIRMAR"         // Titulo en la barra del cuadro
                             , JOptionPane.DEFAULT_OPTION       // Tipo de opciones
                             , JOptionPane.INFORMATION_MESSAGE  // Tipo de mensaje (icono)
                             , null                             // Icono (ninguno)
                             , opciones                         // Opciones personalizadas
                             , null                             // Opcion por defecto
                           );
        if (opcion == JOptionPane.YES_OPTION){
            try{
        String status="0";
        
        this.bd.Conectar();
               
  // SENTENCIA SQL PARA ELIMINACION LOGICA DE UN SERVICIO             
           this.bd.setStm("UPDATE productos SET status='"+status+"'  WHERE codigo= '"+this.textCodigo.getText()+"'");
            mensaje = "SE HA ELIMINADO";
              JOptionPane.showMessageDialog(null, mensaje);
                 limpiar();
                BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Servicio eliminado correctamente");

                 //this.bd.Close();
                } catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al eliminar un servicio:{0}", ex.getMessage());
            }
     
        }
             if (opcion == JOptionPane.NO_OPTION){
                   JOptionPane.showMessageDialog(this,"NO SE ELIMINO","ERROR",JOptionPane.WARNING_MESSAGE);  
              }
 
    
    }  
    
  // METODO QUE ME PERMITE CONSULTAR EN LA BASE DE DATOS Y PASAR LA INFORMACION A LA INTERFAS DE LOS SERVICIOS  
  private void consultar() {
        try{
            new ConsultaServicio(this, true, bd, this, this.nomb_user, this.cod_user).setVisible(true);
 
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Error de conexion!!", "Error", JOptionPane.ERROR_MESSAGE);
            BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error de conexion hacia la base de datos:{0}", ex.getMessage());

            return;
        }
       
    
    
    
    }
// METODO QUE ME PERMITE MODIFICAR LOS DATOS DE LOS SERVICIOS
    private void modificar() {
        
      
String  nombre  =       textNombre.getText()  ;
String  codigo  =       textCodigo.getText()  ;
String  descripcion=    textCantidad.getText()  ;
String  precio   =      textPrecio.getText();
String  categoria   =   String.valueOf(CbxCategoria.getSelectedItem());


String  status  =  "1";                      
String  sSQL    =   "";
String  mensaje =   "";       


       if(textCodigo.getText().equals(""))
    
                {    JOptionPane.showMessageDialog(this,"INGRESAR CODIGO","ERROR",JOptionPane.WARNING_MESSAGE);
     }else   
                if(textNombre.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR NOMBRE","ERROR",JOptionPane.WARNING_MESSAGE);
    
       
    }
         else       if(textCantidad.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR DESCRIPCION   ","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
              } else       if(textPrecio.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR PRECIO","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
              } else       if(categoria.equals("SELEC"))
    {
       JOptionPane.showMessageDialog(this,"SELECCIONAR CATEGORIA","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
              }
         
        
       
        else{ 
         
    
            String[] opciones = {"ACEPTAR", "CANCELAR"};
            int opcion = JOptionPane.showOptionDialog(
                               null                             //componente
                             , "PRESIONE ACEPTAR PARA MODIFICAR LOS DATOS ?"            // Mensaje
                             , "CONFIRMAR"         // Titulo en la barra del cuadro
                             , JOptionPane.DEFAULT_OPTION       // Tipo de opciones
                             , JOptionPane.INFORMATION_MESSAGE  // Tipo de mensaje (icono)
                             , null                             // Icono (ninguno)
                             , opciones                         // Opciones personalizadas
                             , null                             // Opcion por defecto
                           );
              if (opcion == JOptionPane.YES_OPTION)
  {  try{
           this.bd.Conectar();
           String categorias = "";
           
           switch(String.valueOf(this.CbxCategoria.getSelectedItem())){
               case "Comida" : categorias = "1"; break;
               case "Bebidas": categorias = "2"; break;
               case "Golosinas" : categorias = "3"; break;
           }
           
   this.bd.setStm("UPDATE productos SET nombre='" + textNombre.getText() + "', Descripcion='" + textDescripcion1.getText() + "', Precio='"
                    + textPrecio.getText() + "', Categorias='" + categorias + "', Cant='" + textCantidad.getText() + "' WHERE codigo ='" + textCodigo.getText() + "'");
                              JOptionPane.showMessageDialog(null, "MODIFICADO");
                                    limpiar();
          BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Servicio modificado en la base de datos");

                                    //this.bd.Close();

    } catch (SQLException ex) {

                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al modificar un servicio en la base de datos:{0}", ex.getMessage());

    }   } if (opcion == JOptionPane.NO_OPTION){
                   JOptionPane.showMessageDialog(this,"NO SE MODIFICO","ERROR",JOptionPane.WARNING_MESSAGE);  
              }
 
}
        
        
    }

    private void cargarCombo() {
        try{
           this.bd.Conectar();
           this.bd.setResult("SELECT nombre FROM categoria");
           ResultSet result = this.bd.getResult();
           while(result.next()){
               this.CbxCategoria.addItem(result.getString(1));
           }
        }catch(SQLException ex){
          BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al carga un combobox:{0}", ex.getMessage());

        }
    }

    private void activar() {
         if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
           try{
               this.bd.Conectar();
               this.bd.setStm("UPDATE productos SET status = '1' WHERE codigo='" + this.textCodigo.getText() + "'");
               BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Servicio activado correctamente");

           } catch (SQLException ex){
             BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al activar un servicio en la base de datos:{0}", ex.getMessage());

           }
       }
        else {
           JOptionPane.showMessageDialog(this, "Debe llenar todos los campos!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
            BitacoraVentanas.LOG_RAIZ.log(Level.WARNING, "Error al llenar los campos");

       }
    }

    private void verificarUsuario(String cod_user) {
        if(!cod_user.equalsIgnoreCase("1")){
            this.btnActivar.setVisible(false);
        }else{
            this.btnActivar.setVisible(true);
        }
    }

}
