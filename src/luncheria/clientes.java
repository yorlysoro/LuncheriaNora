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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class clientes extends javax.swing.JFrame {
  private javax.swing.JTextField[] lcampos = new javax.swing.JTextField[6];
  private javax.swing.JComboBox[] lcombos = new javax.swing.JComboBox[1];
     private VALIDACION.Validacion vadid = new Validacion();
     private basedatos bd;
    private Calendar Cal;
    private String fec;
    private String cod;
    private String nomb_user;
    private String cod_user;
    public clientes(basedatos bd, String nomb_user, String cod_user) {
         BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Iniciando la ventana de clientes");

        initComponents();
        this.bd = bd;
        this.Cal = Calendar.getInstance();
        this.fec =Cal.get(Cal.DATE)+"/"+(Cal.get(Cal.MONTH)+1)+"/"+ Cal.get(Cal.YEAR);
        this.txtfecha.setText(fec);
        ImageIcon caldr = new ImageIcon(getClass().getResource("/imagenes/calendar.png"));
        Icon fondo1=new ImageIcon(caldr.getImage().getScaledInstance(calendar.getWidth(),calendar.getHeight(), Image.SCALE_DEFAULT));
        calendar.setIcon(fondo1);
        this.repaint();
        this.cod_user = cod_user;
        this.nomb_user = nomb_user;
        
        this.lcampos[0] = this.textcedula;
        this.lcampos[1] = this.textnombres;
        this.lcampos[2] = this.textapellido;
        this.lcampos[3] = this.textdireccion;
        this.lcampos[4] = this.textcorreo;
        this.lcampos[5] = this.textlfno;
        this.lcombos[0] = this.comboTipoTelef;
  //VALIDAR TEXTO DE LOS JTEXTFIEL "CASILLAS DE TEXTO"
        
        SNumeros(textcedula);
        SNumeros(textlfno);
        SLetras(textnombres);
        SLetras(textapellido);
        ValidarCorreo(textcorreo);
        this.setLocationRelativeTo(null);
        this.cargarComboTipo();
        this.verificarUsuario(cod_user);
 // SE REALIZA EL LLAMADO HA EL METODO LIMPIAR
       limpiar();

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
  public void ValidarCorreo (JTextField a)
    {
        a.addKeyListener(new KeyAdapter()
        {
            public void keyTyped( KeyEvent e)
            {
                char c=e. getKeyChar();
                if(!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c) && c != '@' && c != '.')
                {
                    getToolkit().beep();;
                    e.consume();
                }
            }
        });
    }
    
    private boolean validarCorreo(char[] correo) {
        int contA = 0, contP = 0;
        for(int i = 0; i < correo.length; i++){
            if(correo[i] == '@')
                contA++;
            else if(correo[i] == '.')
                contP++;
        }
        if(contA == 0 || contA > 1)
            return false;
        else if(contP == 0 || contP > 1)
            return false;
        
        return true;
    }
    // SE LIMITA LOS CUADROS DE TEXTOS A UN LIMITE ESPECIFICO DE CARACTERES ATRAVEZ 
    //DE UNA CLASE DE HERNCIA QUE SE ENCUENTRA EN EL PAQUETE DE VALIDACION 
    //LLAMADA LIMITAR    
         
         
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registrousuario = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        txtfecha = new javax.swing.JTextField();
        calendar = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        status = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        textstatus = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        icocedula = new javax.swing.JLabel();
        cedula = new javax.swing.JLabel();
        textcedula = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        nombres = new javax.swing.JLabel();
        textnombres = new javax.swing.JTextField();
        iconombre1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        direccion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        textdireccion = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        correo = new javax.swing.JLabel();
        textcorreo = new javax.swing.JTextField();
        icocorreo = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        tlfno = new javax.swing.JLabel();
        textlfno = new javax.swing.JTextField();
        icotlfno = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        guardar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        consultar = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        textapellido = new javax.swing.JTextField();
        nombres1 = new javax.swing.JLabel();
        iconombre2 = new javax.swing.JLabel();
        comboTipoTelef = new javax.swing.JComboBox<>();
        tlfno1 = new javax.swing.JLabel();
        btnActivar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Clientes");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setIconImages(null);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(700, 510));
        setPreferredSize(new java.awt.Dimension(700, 530));
        setResizable(false);
        getContentPane().setLayout(null);

        registrousuario.setFont(new java.awt.Font("Malgun Gothic", 3, 30)); // NOI18N
        registrousuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        registrousuario.setText("Registro de Clientes");
        getContentPane().add(registrousuario);
        registrousuario.setBounds(10, 10, 340, 50);

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));
        jPanel3.setLayout(null);

        fecha.setBackground(new java.awt.Color(255, 255, 255));
        fecha.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fecha.setText("Fecha");
        jPanel3.add(fecha);
        fecha.setBounds(500, 10, 70, 20);

        txtfecha.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        txtfecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfecha.setBorder(null);
        txtfecha.setEnabled(false);
        txtfecha.setOpaque(false);
        jPanel3.add(txtfecha);
        txtfecha.setBounds(500, 30, 80, 20);

        calendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendar.png"))); // NOI18N
        jPanel3.add(calendar);
        calendar.setBounds(470, 30, 20, 20);

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator4);
        jSeparator4.setBounds(500, 50, 80, 10);

        status.setBackground(new java.awt.Color(255, 255, 255));
        status.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        status.setForeground(new java.awt.Color(255, 255, 255));
        status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status.setText("Status");
        jPanel3.add(status);
        status.setBounds(610, 0, 60, 30);

        info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/info.png"))); // NOI18N
        jPanel3.add(info);
        info.setBounds(600, 30, 16, 20);

        textstatus.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textstatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textstatus.setBorder(null);
        textstatus.setEnabled(false);
        textstatus.setOpaque(false);
        textstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textstatusActionPerformed(evt);
            }
        });
        jPanel3.add(textstatus);
        textstatus.setBounds(620, 30, 40, 20);

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator3);
        jSeparator3.setBounds(620, 50, 40, 10);

        icocedula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icocedula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profile.png"))); // NOI18N
        jPanel3.add(icocedula);
        icocedula.setBounds(22, 110, 30, 30);

        cedula.setBackground(new java.awt.Color(255, 255, 255));
        cedula.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        cedula.setForeground(new java.awt.Color(255, 255, 255));
        cedula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cedula.setText("Cedula");
        jPanel3.add(cedula);
        cedula.setBounds(70, 80, 60, 30);

        textcedula.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textcedula.setForeground(new java.awt.Color(255, 255, 255));
        textcedula.setBorder(null);
        textcedula.setOpaque(false);
        textcedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textcedulaActionPerformed(evt);
            }
        });
        textcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textcedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textcedulaKeyTyped(evt);
            }
        });
        jPanel3.add(textcedula);
        textcedula.setBounds(70, 110, 170, 30);

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator5);
        jSeparator5.setBounds(70, 140, 170, 10);

        nombres.setBackground(new java.awt.Color(255, 255, 255));
        nombres.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        nombres.setForeground(new java.awt.Color(255, 255, 255));
        nombres.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombres.setText("Nombres");
        jPanel3.add(nombres);
        nombres.setBounds(70, 150, 80, 30);

        textnombres.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textnombres.setForeground(new java.awt.Color(255, 255, 255));
        textnombres.setBorder(null);
        textnombres.setOpaque(false);
        textnombres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textnombresActionPerformed(evt);
            }
        });
        textnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textnombresKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textnombresKeyTyped(evt);
            }
        });
        jPanel3.add(textnombres);
        textnombres.setBounds(70, 180, 250, 30);

        iconombre1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        jPanel3.add(iconombre1);
        iconombre1.setBounds(20, 180, 30, 30);

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator7);
        jSeparator7.setBounds(70, 210, 250, 10);

        direccion.setBackground(new java.awt.Color(255, 255, 255));
        direccion.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        direccion.setForeground(new java.awt.Color(255, 255, 255));
        direccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        direccion.setText("Direccion");
        jPanel3.add(direccion);
        direccion.setBounds(60, 220, 100, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dir.png"))); // NOI18N
        jPanel3.add(jLabel1);
        jLabel1.setBounds(20, 240, 40, 40);

        textdireccion.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textdireccion.setForeground(new java.awt.Color(255, 255, 255));
        textdireccion.setBorder(null);
        textdireccion.setOpaque(false);
        textdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textdireccionActionPerformed(evt);
            }
        });
        textdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textdireccionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textdireccionKeyTyped(evt);
            }
        });
        jPanel3.add(textdireccion);
        textdireccion.setBounds(70, 250, 600, 30);

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator6);
        jSeparator6.setBounds(70, 280, 600, 10);

        correo.setBackground(new java.awt.Color(255, 255, 255));
        correo.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        correo.setForeground(new java.awt.Color(255, 255, 255));
        correo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        correo.setText("Correo");
        jPanel3.add(correo);
        correo.setBounds(70, 280, 60, 30);

        textcorreo.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textcorreo.setForeground(new java.awt.Color(255, 255, 255));
        textcorreo.setBorder(null);
        textcorreo.setOpaque(false);
        textcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textcorreoActionPerformed(evt);
            }
        });
        textcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textcorreoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textcorreoKeyTyped(evt);
            }
        });
        jPanel3.add(textcorreo);
        textcorreo.setBounds(70, 310, 320, 30);

        icocorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mail.png"))); // NOI18N
        jPanel3.add(icocorreo);
        icocorreo.setBounds(20, 310, 32, 30);

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator8);
        jSeparator8.setBounds(70, 340, 320, 10);

        tlfno.setBackground(new java.awt.Color(255, 255, 255));
        tlfno.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        tlfno.setForeground(new java.awt.Color(255, 255, 255));
        tlfno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tlfno.setText("Telefono");
        jPanel3.add(tlfno);
        tlfno.setBounds(70, 350, 80, 20);

        textlfno.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textlfno.setForeground(new java.awt.Color(255, 255, 255));
        textlfno.setBorder(null);
        textlfno.setOpaque(false);
        textlfno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textlfnoActionPerformed(evt);
            }
        });
        textlfno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textlfnoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textlfnoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textlfnoKeyTyped(evt);
            }
        });
        jPanel3.add(textlfno);
        textlfno.setBounds(70, 370, 320, 30);

        icotlfno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/phone.png"))); // NOI18N
        jPanel3.add(icotlfno);
        icotlfno.setBounds(22, 370, 30, 30);

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator10);
        jSeparator10.setBounds(70, 400, 320, 10);

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Guardar.png"))); // NOI18N
        guardar.setBorderPainted(false);
        guardar.setContentAreaFilled(false);
        guardar.setEnabled(false);
        guardar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Guardarhover.png"))); // NOI18N
        guardar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Guardarhover.png"))); // NOI18N
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });
        jPanel3.add(guardar);
        guardar.setBounds(10, 440, 130, 60);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        eliminar.setBorderPainted(false);
        eliminar.setContentAreaFilled(false);
        eliminar.setEnabled(false);
        eliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminarhover.png"))); // NOI18N
        eliminar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminarhover.png"))); // NOI18N
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPanel3.add(eliminar);
        eliminar.setBounds(140, 440, 130, 59);

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificarhover.png"))); // NOI18N
        modificar.setBorderPainted(false);
        modificar.setContentAreaFilled(false);
        modificar.setEnabled(false);
        modificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        modificar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPanel3.add(modificar);
        modificar.setBounds(280, 440, 120, 59);

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
        jPanel3.add(consultar);
        consultar.setBounds(400, 440, 140, 60);

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(jSeparator9);
        jSeparator9.setBounds(400, 210, 270, 10);

        textapellido.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textapellido.setForeground(new java.awt.Color(255, 255, 255));
        textapellido.setBorder(null);
        textapellido.setOpaque(false);
        textapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textapellidoActionPerformed(evt);
            }
        });
        textapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textapellidoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textapellidoKeyTyped(evt);
            }
        });
        jPanel3.add(textapellido);
        textapellido.setBounds(400, 180, 270, 30);

        nombres1.setBackground(new java.awt.Color(255, 255, 255));
        nombres1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        nombres1.setForeground(new java.awt.Color(255, 255, 255));
        nombres1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombres1.setText("Apellidos");
        jPanel3.add(nombres1);
        nombres1.setBounds(400, 150, 80, 30);

        iconombre2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        jPanel3.add(iconombre2);
        iconombre2.setBounds(360, 180, 30, 30);

        comboTipoTelef.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));
        comboTipoTelef.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTipoTelefItemStateChanged(evt);
            }
        });
        jPanel3.add(comboTipoTelef);
        comboTipoTelef.setBounds(410, 370, 90, 30);

        tlfno1.setBackground(new java.awt.Color(255, 255, 255));
        tlfno1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        tlfno1.setForeground(new java.awt.Color(255, 255, 255));
        tlfno1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tlfno1.setText("Tipo");
        jPanel3.add(tlfno1);
        tlfno1.setBounds(400, 340, 50, 20);

        btnActivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Btn_activar.png"))); // NOI18N
        btnActivar.setBorderPainted(false);
        btnActivar.setContentAreaFilled(false);
        btnActivar.setFocusPainted(false);
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });
        jPanel3.add(btnActivar);
        btnActivar.setBounds(530, 440, 160, 60);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 700, 510);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textstatusActionPerformed

    private void textcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textcedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textcedulaActionPerformed

    private void textnombresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textnombresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textnombresActionPerformed

    private void textdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textdireccionActionPerformed

    private void textcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textcorreoActionPerformed

    private void textlfnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textlfnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textlfnoActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
// SE REALIZA EL LLAMADO HA EL METODO 
        guardar()   ;   
    }//GEN-LAST:event_guardarActionPerformed

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarActionPerformed
// SE REALIZA EL LLAMADO HA EL METODO 
        consultar();
    }//GEN-LAST:event_consultarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
 // SE REALIZA EL LLAMADO HA EL METODO 
        eliminar();        
    }//GEN-LAST:event_eliminarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
// SE REALIZA EL LLAMADO HA EL METODO 
        modificar();       
    }//GEN-LAST:event_modificarActionPerformed

    private void textapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textapellidoActionPerformed

    private void textcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcedulaKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            this.textnombres.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombos) || this.textcedula.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textcedulaKeyPressed

    private void textapellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textapellidoKeyPressed
       if(evt.getKeyCode() == evt.VK_ENTER)
            this.textdireccion.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombos) || this.textapellido.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textapellidoKeyPressed

    private void textnombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnombresKeyPressed
         if(evt.getKeyCode() == evt.VK_ENTER)
            this.textapellido.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombos) || this.textnombres.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textnombresKeyPressed

    private void textdireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textdireccionKeyPressed
         if(evt.getKeyCode() == evt.VK_ENTER)
            this.textcorreo.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombos) || this.textdireccion.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textdireccionKeyPressed

    private void textcorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcorreoKeyPressed
           if(evt.getKeyCode() == evt.VK_ENTER)
            this.textlfno.requestFocus();
          if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombos) || this.textcorreo.getText().equalsIgnoreCase("") && !this.validarCorreo(textcorreo.getText().toCharArray())){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos) && this.validarCorreo(textcorreo.getText().toCharArray())){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textcorreoKeyPressed

    private void textlfnoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textlfnoKeyPressed
       if(evt.getKeyCode() == evt.VK_ENTER && this.vadid.campos(lcampos) && this.textlfno.getText().length()== VALIDACION.limitar.telef){
            this.guardar();
            this.limpiar();
       }
           
        if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos) && this.textlfno.getText().length() == VALIDACION.limitar.telef){
            this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
        }
        if(this.textlfno.getText().equalsIgnoreCase("")){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombos) || this.textlfno.getText().length() != VALIDACION.limitar.telef ){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
    }//GEN-LAST:event_textlfnoKeyPressed

    private void comboTipoTelefItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTipoTelefItemStateChanged
        // TODO add your handling code here:
         if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos)){
            this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
        }
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombos)){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
    }//GEN-LAST:event_comboTipoTelefItemStateChanged

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        // TODO add your handling code here:
        this.activar();
    }//GEN-LAST:event_btnActivarActionPerformed

    private void textcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcedulaKeyTyped
        // TODO add your handling code here:
        if(textcedula.getText().length() == limitar.cedula){
            evt.consume();
        }
    }//GEN-LAST:event_textcedulaKeyTyped

    private void textnombresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnombresKeyTyped
        // TODO add your handling code here:
        if(textnombres.getText().length() == limitar.nomapell){
            evt.consume();
        }
    }//GEN-LAST:event_textnombresKeyTyped

    private void textapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textapellidoKeyTyped
        // TODO add your handling code here:
        if(textapellido.getText().length() == limitar.nomapell){
            evt.consume();
        }
    }//GEN-LAST:event_textapellidoKeyTyped

    private void textdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textdireccionKeyTyped
        // TODO add your handling code here:
        if(textdireccion.getText().length() == limitar.fulltext){
            evt.consume();
        }
    }//GEN-LAST:event_textdireccionKeyTyped

    private void textcorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcorreoKeyTyped
        // TODO add your handling code here:
        if(textcorreo.getText().length() == limitar.fulltext){
            evt.consume();
        }
    }//GEN-LAST:event_textcorreoKeyTyped

    private void textlfnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textlfnoKeyTyped
        // TODO add your handling code here:
        if(textlfno.getText().length() == limitar.telef){
            evt.consume();
        }
    }//GEN-LAST:event_textlfnoKeyTyped

    private void textlfnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textlfnoKeyReleased
        // TODO add your handling code here:
          if(evt.getKeyCode() == evt.VK_ENTER && this.vadid.campos(lcampos) && this.textlfno.getText().length() == VALIDACION.limitar.telef){
            this.guardar();
            this.limpiar();
       }
           
        if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos) && this.textlfno.getText().length() == VALIDACION.limitar.telef){
            this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
        }
        if(this.textlfno.getText().equalsIgnoreCase("")){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombos) || this.textlfno.getText().length() == 0 ){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
    }//GEN-LAST:event_textlfnoKeyReleased

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JLabel calendar;
    private javax.swing.JLabel cedula;
    private javax.swing.JComboBox<String> comboTipoTelef;
    private javax.swing.JButton consultar;
    private javax.swing.JLabel correo;
    private javax.swing.JLabel direccion;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel fecha;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel icocedula;
    private javax.swing.JLabel icocorreo;
    private javax.swing.JLabel iconombre1;
    private javax.swing.JLabel iconombre2;
    private javax.swing.JLabel icotlfno;
    private javax.swing.JLabel info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton modificar;
    private javax.swing.JLabel nombres;
    private javax.swing.JLabel nombres1;
    private javax.swing.JLabel registrousuario;
    private javax.swing.JLabel status;
    private javax.swing.JTextField textapellido;
    private javax.swing.JTextField textcedula;
    private javax.swing.JTextField textcorreo;
    private javax.swing.JTextField textdireccion;
    private javax.swing.JTextField textlfno;
    private javax.swing.JTextField textnombres;
    private javax.swing.JTextField textstatus;
    private javax.swing.JLabel tlfno;
    private javax.swing.JLabel tlfno1;
    private javax.swing.JTextField txtfecha;
    // End of variables declaration//GEN-END:variables

     //METODO QUE ME PERMITE GUARDAR LOS NUEVOS REGISTROS DE CLIENTE A LA BASE DE DATOS   

    private void guardar() {
String ci =textcedula.getText()      ; 


//PASANDO EL TEXTO DE LOS CUADROS DE TEXTOS A VARIABLES
    
String  fechaR  =    fec;
String  nombre  =    textnombres.getText()  ;
String  direccion=   textdireccion.getText()  ;
String  correo   =   textcorreo.getText();
String  telefono =   textlfno.getText()  ;


String status="1"      ;                      
String SQL="";
String mensaje ="";

  // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO

       if(textcedula.getText().equals("")){    
           
           JOptionPane.showMessageDialog(this,"INGRESAR CEDULA","ERROR",JOptionPane.WARNING_MESSAGE);
           textcedula.requestFocus();
     }else   
      // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
            if(textnombres.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR NOMBRE","ERROR",JOptionPane.WARNING_MESSAGE);
    
       
    }
    // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
       else       if(textdireccion.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR DIRECCION   ","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
     // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
           } else       if(textcorreo.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR CORREO","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
       // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
         } else       if(textlfno.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR TELEFONO","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
              }
         
        
       
        else{ 
         
    // MENSAJE DE CONFIRMACION PARA GUARDAR CLIENTE
  
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
        Connection con = this.bd.getCon();
               
  // SENTENCIA SQL PARA GUARDAR NUEVOS CLIENTES A LA BASE DE DATOS 
            String tipo_telef = String.valueOf(this.comboTipoTelef.getSelectedItem());
            switch(tipo_telef){
                case "Movil" : tipo_telef = "1"; break;
                case "Casa"  : tipo_telef = "2"; break;
            }
            this.bd.setStm("INSERT INTO clientes(cedula,nombres,apellidos,direccion,correo,telefono, tipo_telefono, status)"
                    + " VALUES " + "('" + this.textcedula.getText() + "','"+ this.textnombres.getText() + "', '" + this.textapellido.getText() + "', '"
                    + this.textdireccion.getText() + "', '" + this.textcorreo.getText() + "', '" + this.textlfno.getText() + "','" + tipo_telef + "', 1)");
            mensaje = "SE HA GUARDADO DE MANERA CORRECTA";
              JOptionPane.showMessageDialog(null, mensaje);
                limpiar();    
                BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Datos guardados con exito");

                //this.bd.Close();
                } catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al guardar datos de un cliente:{0}", ex.getMessage());
            JOptionPane.showMessageDialog(this," YA EXISTE UN CLIENTE CON DICHA CEDULA","ERROR",JOptionPane.WARNING_MESSAGE);
                }
               
    
    
    
    } if (opcion == JOptionPane.NO_OPTION){
                   JOptionPane.showMessageDialog(this,"NO SE GUARDO","ERROR",JOptionPane.WARNING_MESSAGE);  
              }
 
}}
 //METODO QUE PERMITE VACIAR TODAS LAS CASILLAS DE TEXTOS   

    private void limpiar() {
  //OBTENER FECHA DE LA PC
   
    
    txtfecha.setText(fec);    
    textcedula.setText(""); 
    textnombres.setText("");
    textapellido.setText("");
    textdireccion.setText("");
    textcorreo.setText("");
    textlfno.setText("");
 
    textstatus.setText("");
    this.comboTipoTelef.setSelectedItem("SELECCIONE");

 
    this.guardar.setEnabled(false);
    this.eliminar.setEnabled(false);
    this.modificar.setEnabled(false);
    }
 // METODO QUE PERMITE REALIZAR UNA ELIMINACION LOGICA DE LOS REGISTROS DE  CLIENTES
  private void eliminar() {

     String mensaje="";

    // MENSAJE DE CONFIRMACION PARA LA ELIMINACION LOGICA DE LOS REGISTROS DE  CLIENTES
     
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
        String status="0",
        ci=textcedula.getText();
        

        this.bd.Conectar();
        Connection con= this.bd.getCon();
               
  //SENTENCI SQL QUE PERMITE LA ELIMINACION LOGICA A LOS REGISTROS DE LOS CLIENTES             
            this.bd.setStm("UPDATE clientes SET status='"+status+"'  WHERE cedula= '"+ci+"'");
            mensaje = "SE HA ELIMINADO";
              JOptionPane.showMessageDialog(null, mensaje);
                 limpiar();
                 BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Cliente eliminado con exito");

                 //this.bd.Close();
                } catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al intentar eliminar un cliente:{0}", ex.getMessage());
            }
     
        }
             if (opcion == JOptionPane.NO_OPTION){
                   JOptionPane.showMessageDialog(this,"NO SE ELIMINO","ERROR",JOptionPane.WARNING_MESSAGE);  
              }
 
    
    }  
    
 //METODO QUE ME PERMITE REALIZA UNA CONSULTA EN LA BASE DE DATOS Y MOSTRARLA EN LA INTERFAZ DE LOS CLIENTES   
  private void consultar() {
         try{
            new ConsultaCliente(this,true, bd, this, this.nomb_user, this.cod_user).setVisible(true);
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(this, "Erro al conectar!!", "Error", JOptionPane.ERROR_MESSAGE);
            BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al conectar con la base de datos:{0}", ex.getMessage());

            return;
        }
      
         
    
    
    
    }  
// METODO QUE ME PERMITE MODIFICAR LOS REGISTROS DE LOS CLIENTES
    private void modificar() {
        
 
  String cedula =textcedula.getText()      ; 

String  nombre  =    textnombres.getText()  ;
String apellido = textapellido.getText();
String  direccion=    textdireccion.getText()  ;
String  correo   =   textcorreo.getText();
String  telefono =   textlfno.getText()  ;


String  status="1"      ;                      
String sSQL="";
String mensaje ="";       


          // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
    if(textcedula.getText().equals(""))
    
                {    JOptionPane.showMessageDialog(this,"INGRESAR CEDULA","ERROR",JOptionPane.WARNING_MESSAGE);
     }else   
          // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
             if(textnombres.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR NOMBRE","ERROR",JOptionPane.WARNING_MESSAGE);
    
       
    }
         // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
       else       if(textdireccion.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR DIRECCION   ","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
         // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
            } else       if(textcorreo.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR CORREO","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
        // VALIDAR QUE EL CUADRO DE TEXTO  NO ESTE VACIO
             } else       if(textlfno.getText().equals(""))
    {
       JOptionPane.showMessageDialog(this,"INGRESAR TELEFONO","ERROR",JOptionPane.WARNING_MESSAGE);
       
       
    
              }
         
        
       
        else{ 
         
    // MENSAJE DE CONFIRMACION PARA MODIFICAR LOS REGISTROS DE LOS CLIENTES
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
         String tipo_telef = String.valueOf(this.comboTipoTelef.getSelectedItem());
         switch(tipo_telef){
             case "Movil" : tipo_telef = "1"; break;
             case "Casa"  : tipo_telef = "2"; break;
         }
 // SENTENCIA SQL QUE PERMITE MODIFICAR LOS REGISTROS DE LOS CLIENTES          
    this.bd.setStm("UPDATE clientes SET nombres='" + this.textnombres.getText() + "', apellidos='" + this.textapellido.getText() + "', direccion='"
    + this.textdireccion.getText() +  "', correo='" + this.textcorreo.getText() + "', telefono='" + this.textlfno.getText() + "', tipo_telefono = '" + tipo_telef + "', status='1' WHERE cedula='" + this.textcedula.getText() + "'");
                              JOptionPane.showMessageDialog(null, "MODIFICADO");
                                    limpiar();
                      BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Cliente modificado con exito");

                                    //this.bd.Close();

    } catch (SQLException ex) {

                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al modificar el cliente:{0}", ex.getMessage());

    }
    
    } if (opcion == JOptionPane.NO_OPTION){
                   JOptionPane.showMessageDialog(this,"NO SE GUARDO","ERROR",JOptionPane.WARNING_MESSAGE);  
              }
 
}
    
    
    }

    public void setCod(String cod) {
        this.cod = cod;
        try{
        this.bd.Conectar();
        this.bd.setResult("SELECT * FROM clientes WHERE cedula= '"+this.cod+"'");
        ResultSet result = this.bd.getResult();
        if(result!=null){
            while(result.next()){
//PASAR DATOS DE LA BASE DE DATOS A LA INTERFAZ DE LOS CLIENTES     
   
           textcedula.setText(""+result.getString(1));       
           textnombres.setText(""+result.getString(2));
           textapellido.setText(""+result.getString(3));
           textdireccion.setText(""+result.getString(4));     
           textcorreo.setText(""+result.getString(5)); 
           textlfno.setText(""+result.getString(6)); 
           textstatus.setText(""+result.getInt(7));
           comboTipoTelef.setSelectedItem(result.getString(8).equalsIgnoreCase("1") ? "Movil" : "Casa");
                
                  }
             this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
        }      
    } catch (SQLException ex) {
           BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al cargar datos:{0}", ex.getMessage());
    }
    }

    private void cargarComboTipo() {
        try{
            this.bd.Conectar();
            this.bd.setResult("SELECT nombre FROM tipo_telefono");
            ResultSet result = this.bd.getResult();
            
            while(result.next()){
                this.comboTipoTelef.addItem(result.getString(1));
            }
        }catch (SQLException ex){
          BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al cargar datos:{0}", ex.getMessage());

        }
    }

    private void activar() {
        if(this.vadid.campos(lcampos) && this.vadid.combox(lcombos)){
           try{
               this.bd.Conectar();
               this.bd.setStm("UPDATE clientes SET status = '1' WHERE cedula='" + this.textcedula.getText() + "'");
                BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Cliente activado con exito");

           } catch (SQLException ex){
             BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al activar un cliente:{0}", ex.getMessage());

           }
       }
        else {
           JOptionPane.showMessageDialog(this, "Debe llenar todos los campos!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
           BitacoraVentanas.LOG_RAIZ.log(Level.WARNING, "Error al llenar campos");

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
