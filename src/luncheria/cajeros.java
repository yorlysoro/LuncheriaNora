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
import BDA.ConfigurarPreguntas;
import BDA.Encriptacion;

public class cajeros extends javax.swing.JFrame {
        
     private boolean mostrar = true;
     private javax.swing.JTextField[] lcampos = new javax.swing.JTextField[8];
     private javax.swing.JComboBox[] lcombox = new javax.swing.JComboBox[3];
     private VALIDACION.Validacion vadid = new Validacion();
     private basedatos bd;
     private String cod;
     private Calendar Cal;
     private String fec;
     private String cod_user;
     private String nomb_user;
   
    public cajeros(basedatos bd, String nomb_user, String cod_user) {
        initComponents();
        BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Iniciando la cajeros");

        ImageIcon caldr = new ImageIcon(getClass().getResource("/imagenes/calendar.png"));
        Icon fondo1=new ImageIcon(caldr.getImage().getScaledInstance(calendar.getWidth(),calendar.getHeight(), Image.SCALE_DEFAULT));
        calendar.setIcon(fondo1);
        this.repaint();
        lcampos[0] = textcedula;
        lcampos[1] = textnombre;
        lcampos[2] = textapellido;
        lcampos[3] = textcorreo;
        lcampos[4] = texttlfn;
        lcampos[5] = textnomusuario;
        lcampos[6] = textpass;
        lcampos[7] = textrepass;
         
        lcombox[0] = comboxgene;
        lcombox[1] = comboxnivel;
        lcombox[2] = comboTipoTelef;
        
        this.bd = bd;
        this.Cal = Calendar.getInstance();
        this.fec = Cal.get(Cal.DATE)+"/"+(Cal.get(Cal.MONTH)+1)+"/"+ Cal.get(Cal.YEAR);
        this.setLocationRelativeTo(null);
        this.cargarCombos();
        this.cod_user = cod_user;
        this.nomb_user = nomb_user;
 //VALIDAR TEXTO DE LOS JTEXTFIEL "CASILLAS DE TEXTO"
        SLetras(textnombre);
        SLetras(textapellido);
        SNumeros(textcedula);
        SNumeros(texttlfn);
        ValidarCorreo(textcorreo);
        this.verificarUsuario(cod_user);
// SE REALIZA EL LLAMADO HA EL METODO LIMPIAR

 limpiar();
    
   
    }

    public void setCod(String cod) {
           this.cod = cod;
        try{
            this.bd.Conectar();
            this.bd.setResult("SELECT * FROM empleados WHERE cedula ='" + this.cod + "'");
            ResultSet result = this.bd.getResult();
            while(result.next()){
                this.textcedula.setText(""+result.getString(1));
                this.textnombre.setText(""+result.getString(2));
                this.textapellido.setText(""+result.getString(3));
                this.textcorreo.setText(""+result.getString(4));
                this.texttlfn.setText(""+result.getString(5));
                this.comboxgene.setSelectedItem(result.getString(6).equalsIgnoreCase("M") ? "MASCULINO" : "FEMENINO");
            }
            this.bd.setResult("SELECT * FROM usuarios WHERE cedula_empleado ='" + this.cod +"'");
            result = this.bd.getResult();
            String nivel = "";
            while(result.next()){
                this.textnomusuario.setText(""+result.getString(3));
                this.textpass.setText(""+result.getString(4));
                this.textrepass.setText(""+result.getString(4));
                nivel = ""+result.getString(5);
                this.textstatus.setText(""+result.getString(6));
            }
            switch(nivel){
                case "1":  this.comboxnivel.setSelectedItem("ADMINISTRADOR"); break;
                case "2":  this.comboxnivel.setSelectedItem("CAJERO"); break;
                case "3":  this.comboxnivel.setSelectedItem("INVENTARIO"); break;
            }
            this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
            ////this.bd.Close();
        }catch (SQLException ex){
           BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al consultar datos:{0}", ex.getMessage());

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
    //DE UNA CLASE DE HERENCIA QUE SE ENCUENTRA EN EL PAQUETE DE VALIDACION 
    //LLAMADA LIMITAR
       

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        registrousuario = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        textfecha = new javax.swing.JTextField();
        calendar = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        status = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        textstatus = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        cedula = new javax.swing.JLabel();
        textcedula = new javax.swing.JTextField();
        icocedula = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        nombre = new javax.swing.JLabel();
        textnombre = new javax.swing.JTextField();
        iconombre = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        apellido = new javax.swing.JLabel();
        textapellido = new javax.swing.JTextField();
        icoapelli = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        correo = new javax.swing.JLabel();
        icocorreo = new javax.swing.JLabel();
        textcorreo = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        tlfno = new javax.swing.JLabel();
        icotlfno = new javax.swing.JLabel();
        texttlfn = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        genero = new javax.swing.JLabel();
        icogenero = new javax.swing.JLabel();
        comboxgene = new javax.swing.JComboBox();
        nivel = new javax.swing.JLabel();
        iconivel = new javax.swing.JLabel();
        comboxnivel = new javax.swing.JComboBox();
        jSeparator9 = new javax.swing.JSeparator();
        nombreusuario = new javax.swing.JLabel();
        icousuario = new javax.swing.JLabel();
        textnomusuario = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        clave = new javax.swing.JLabel();
        icoclave = new javax.swing.JLabel();
        textpass = new javax.swing.JPasswordField();
        jSeparator12 = new javax.swing.JSeparator();
        reclave = new javax.swing.JLabel();
        icoreclave = new javax.swing.JLabel();
        textrepass = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        guardar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnverpass = new javax.swing.JButton();
        btnverrepass = new javax.swing.JButton();
        textvisblepass = new javax.swing.JTextField();
        textvisblerepass = new javax.swing.JTextField();
        tlfno1 = new javax.swing.JLabel();
        comboTipoTelef = new javax.swing.JComboBox<>();
        btnActivar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro de Usuarios");
        setIconImages(null);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(700, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        Fondo.setBackground(new java.awt.Color(51, 153, 255));
        Fondo.setLayout(null);

        registrousuario.setBackground(new java.awt.Color(0, 0, 0));
        registrousuario.setFont(new java.awt.Font("Malgun Gothic", 3, 30)); // NOI18N
        registrousuario.setText("Registro de Usuarios");
        Fondo.add(registrousuario);
        registrousuario.setBounds(20, 10, 320, 50);

        fecha.setBackground(new java.awt.Color(255, 255, 255));
        fecha.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fecha.setText("Fecha");
        Fondo.add(fecha);
        fecha.setBounds(480, 0, 100, 20);

        textfecha.setBackground(new java.awt.Color(255, 255, 153));
        textfecha.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textfecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textfecha.setBorder(null);
        textfecha.setEnabled(false);
        textfecha.setOpaque(false);
        Fondo.add(textfecha);
        textfecha.setBounds(500, 20, 80, 20);

        calendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendar.png"))); // NOI18N
        Fondo.add(calendar);
        calendar.setBounds(470, 20, 20, 20);

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator4);
        jSeparator4.setBounds(500, 40, 80, 10);

        status.setBackground(new java.awt.Color(255, 255, 255));
        status.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        status.setForeground(new java.awt.Color(255, 255, 255));
        status.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        status.setText("Status");
        Fondo.add(status);
        status.setBounds(620, 0, 50, 20);

        info.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/info.png"))); // NOI18N
        Fondo.add(info);
        info.setBounds(600, 20, 16, 20);

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
        Fondo.add(textstatus);
        textstatus.setBounds(630, 20, 40, 20);

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator3);
        jSeparator3.setBounds(630, 40, 40, 10);

        cedula.setBackground(new java.awt.Color(255, 255, 255));
        cedula.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        cedula.setForeground(new java.awt.Color(255, 255, 255));
        cedula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cedula.setText("Cedula");
        Fondo.add(cedula);
        cedula.setBounds(60, 80, 60, 20);

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
        Fondo.add(textcedula);
        textcedula.setBounds(60, 100, 180, 30);

        icocedula.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icocedula.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/profile.png"))); // NOI18N
        Fondo.add(icocedula);
        icocedula.setBounds(20, 100, 32, 30);

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator5);
        jSeparator5.setBounds(60, 130, 180, 10);

        nombre.setBackground(new java.awt.Color(255, 255, 255));
        nombre.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setText("Nombres");
        Fondo.add(nombre);
        nombre.setBounds(60, 130, 80, 30);

        textnombre.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textnombre.setForeground(new java.awt.Color(255, 255, 255));
        textnombre.setBorder(null);
        textnombre.setOpaque(false);
        textnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textnombreActionPerformed(evt);
            }
        });
        textnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textnombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textnombreKeyTyped(evt);
            }
        });
        Fondo.add(textnombre);
        textnombre.setBounds(60, 150, 330, 30);

        iconombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        Fondo.add(iconombre);
        iconombre.setBounds(20, 150, 30, 30);

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator6);
        jSeparator6.setBounds(60, 180, 330, 10);

        apellido.setBackground(new java.awt.Color(255, 255, 255));
        apellido.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        apellido.setForeground(new java.awt.Color(255, 255, 255));
        apellido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        apellido.setText("Apellidos:");
        Fondo.add(apellido);
        apellido.setBounds(50, 190, 100, 20);

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
        Fondo.add(textapellido);
        textapellido.setBounds(60, 210, 330, 30);

        icoapelli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        Fondo.add(icoapelli);
        icoapelli.setBounds(20, 210, 30, 30);

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator7);
        jSeparator7.setBounds(60, 240, 330, 10);

        correo.setBackground(new java.awt.Color(255, 255, 255));
        correo.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        correo.setForeground(new java.awt.Color(255, 255, 255));
        correo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        correo.setText("Correo");
        Fondo.add(correo);
        correo.setBounds(50, 250, 70, 20);

        icocorreo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mail.png"))); // NOI18N
        Fondo.add(icocorreo);
        icocorreo.setBounds(20, 270, 32, 30);

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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textcorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textcorreoKeyTyped(evt);
            }
        });
        Fondo.add(textcorreo);
        textcorreo.setBounds(60, 270, 330, 30);

        jSeparator8.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator8);
        jSeparator8.setBounds(60, 300, 330, 10);

        tlfno.setBackground(new java.awt.Color(255, 255, 255));
        tlfno.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        tlfno.setForeground(new java.awt.Color(255, 255, 255));
        tlfno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tlfno.setText("Telefono");
        Fondo.add(tlfno);
        tlfno.setBounds(60, 310, 80, 20);

        icotlfno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/phone.png"))); // NOI18N
        Fondo.add(icotlfno);
        icotlfno.setBounds(20, 330, 32, 30);

        texttlfn.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        texttlfn.setForeground(new java.awt.Color(255, 255, 255));
        texttlfn.setBorder(null);
        texttlfn.setOpaque(false);
        texttlfn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texttlfnActionPerformed(evt);
            }
        });
        texttlfn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                texttlfnKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                texttlfnKeyTyped(evt);
            }
        });
        Fondo.add(texttlfn);
        texttlfn.setBounds(60, 330, 330, 30);

        jSeparator10.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator10);
        jSeparator10.setBounds(60, 360, 330, 10);

        genero.setBackground(new java.awt.Color(255, 255, 255));
        genero.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        genero.setForeground(new java.awt.Color(255, 255, 255));
        genero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        genero.setText("Genero");
        Fondo.add(genero);
        genero.setBounds(40, 360, 100, 30);

        icogenero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/man-woman.png"))); // NOI18N
        Fondo.add(icogenero);
        icogenero.setBounds(20, 390, 32, 30);

        comboxgene.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        comboxgene.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE", "FEMENINO", "MASCULINO" }));
        comboxgene.setBorder(null);
        comboxgene.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxgeneItemStateChanged(evt);
            }
        });
        comboxgene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxgeneActionPerformed(evt);
            }
        });
        comboxgene.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboxgeneKeyPressed(evt);
            }
        });
        Fondo.add(comboxgene);
        comboxgene.setBounds(60, 390, 150, 30);

        nivel.setBackground(new java.awt.Color(255, 255, 255));
        nivel.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        nivel.setForeground(new java.awt.Color(255, 255, 255));
        nivel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nivel.setText("Nivel");
        Fondo.add(nivel);
        nivel.setBounds(310, 360, 100, 30);

        iconivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tree.png"))); // NOI18N
        Fondo.add(iconivel);
        iconivel.setBounds(290, 390, 32, 30);

        comboxnivel.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        comboxnivel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONE" }));
        comboxnivel.setBorder(null);
        comboxnivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxnivelItemStateChanged(evt);
            }
        });
        comboxnivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxnivelActionPerformed(evt);
            }
        });
        comboxnivel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboxnivelKeyPressed(evt);
            }
        });
        Fondo.add(comboxnivel);
        comboxnivel.setBounds(340, 390, 160, 30);

        jSeparator9.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator9.setToolTipText("");
        Fondo.add(jSeparator9);
        jSeparator9.setBounds(0, 440, 700, 10);

        nombreusuario.setBackground(new java.awt.Color(255, 255, 255));
        nombreusuario.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        nombreusuario.setForeground(new java.awt.Color(255, 255, 255));
        nombreusuario.setText("Nombre de Usuario");
        Fondo.add(nombreusuario);
        nombreusuario.setBounds(60, 450, 180, 30);

        icousuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuario.png"))); // NOI18N
        Fondo.add(icousuario);
        icousuario.setBounds(20, 480, 30, 30);

        textnomusuario.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textnomusuario.setForeground(new java.awt.Color(255, 255, 255));
        textnomusuario.setBorder(null);
        textnomusuario.setOpaque(false);
        textnomusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textnomusuarioActionPerformed(evt);
            }
        });
        textnomusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textnomusuarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textnomusuarioKeyTyped(evt);
            }
        });
        Fondo.add(textnomusuario);
        textnomusuario.setBounds(60, 480, 330, 30);

        jSeparator11.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator11);
        jSeparator11.setBounds(60, 510, 330, 10);

        clave.setBackground(new java.awt.Color(255, 255, 255));
        clave.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        clave.setForeground(new java.awt.Color(255, 255, 255));
        clave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clave.setText("Clave");
        Fondo.add(clave);
        clave.setBounds(60, 510, 50, 30);

        icoclave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/142-key.png"))); // NOI18N
        Fondo.add(icoclave);
        icoclave.setBounds(20, 540, 30, 30);

        textpass.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textpass.setForeground(new java.awt.Color(255, 255, 255));
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textpassKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textpassKeyTyped(evt);
            }
        });
        Fondo.add(textpass);
        textpass.setBounds(60, 540, 190, 30);

        jSeparator12.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator12);
        jSeparator12.setBounds(60, 570, 190, 10);

        reclave.setBackground(new java.awt.Color(255, 255, 255));
        reclave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        reclave.setForeground(new java.awt.Color(255, 255, 255));
        reclave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reclave.setText("Re-clave");
        Fondo.add(reclave);
        reclave.setBounds(350, 510, 60, 30);

        icoreclave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/142-key.png"))); // NOI18N
        Fondo.add(icoreclave);
        icoreclave.setBounds(310, 540, 30, 30);

        textrepass.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        textrepass.setForeground(new java.awt.Color(255, 255, 255));
        textrepass.setBorder(null);
        textrepass.setOpaque(false);
        textrepass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textrepassKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textrepassKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textrepassKeyTyped(evt);
            }
        });
        Fondo.add(textrepass);
        textrepass.setBounds(350, 540, 190, 30);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        Fondo.add(jSeparator2);
        jSeparator2.setBounds(350, 570, 190, 10);

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
        Fondo.add(guardar);
        guardar.setBounds(20, 600, 120, 59);

        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        eliminar.setToolTipText("");
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
        Fondo.add(eliminar);
        eliminar.setBounds(150, 600, 120, 59);

        modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificar.png"))); // NOI18N
        modificar.setBorderPainted(false);
        modificar.setContentAreaFilled(false);
        modificar.setEnabled(false);
        modificar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificarhover.png"))); // NOI18N
        modificar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificarhover.png"))); // NOI18N
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        Fondo.add(modificar);
        modificar.setBounds(280, 600, 120, 60);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultar.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultarhover.png"))); // NOI18N
        jButton5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultarhover.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Fondo.add(jButton5);
        jButton5.setBounds(410, 600, 120, 60);

        btnverpass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/207-eye.png"))); // NOI18N
        btnverpass.setBorder(null);
        btnverpass.setBorderPainted(false);
        btnverpass.setContentAreaFilled(false);
        btnverpass.setDefaultCapable(false);
        btnverpass.setFocusPainted(false);
        btnverpass.setFocusable(false);
        btnverpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverpassActionPerformed(evt);
            }
        });
        Fondo.add(btnverpass);
        btnverpass.setBounds(260, 540, 30, 30);

        btnverrepass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/207-eye.png"))); // NOI18N
        btnverrepass.setBorder(null);
        btnverrepass.setBorderPainted(false);
        btnverrepass.setContentAreaFilled(false);
        btnverrepass.setDefaultCapable(false);
        btnverrepass.setFocusPainted(false);
        btnverrepass.setFocusable(false);
        btnverrepass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverrepassActionPerformed(evt);
            }
        });
        Fondo.add(btnverrepass);
        btnverrepass.setBounds(550, 540, 30, 30);

        textvisblepass.setBorder(null);
        textvisblepass.setEnabled(false);
        textvisblepass.setOpaque(false);
        textvisblepass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textvisblepassActionPerformed(evt);
            }
        });
        Fondo.add(textvisblepass);
        textvisblepass.setBounds(60, 540, 190, 30);

        textvisblerepass.setBorder(null);
        textvisblerepass.setEnabled(false);
        textvisblerepass.setOpaque(false);
        textvisblerepass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textvisblerepassActionPerformed(evt);
            }
        });
        Fondo.add(textvisblerepass);
        textvisblerepass.setBounds(350, 540, 190, 30);

        tlfno1.setBackground(new java.awt.Color(255, 255, 255));
        tlfno1.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        tlfno1.setForeground(new java.awt.Color(255, 255, 255));
        tlfno1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tlfno1.setText("Tipo");
        Fondo.add(tlfno1);
        tlfno1.setBounds(410, 300, 60, 20);

        comboTipoTelef.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE" }));
        Fondo.add(comboTipoTelef);
        comboTipoTelef.setBounds(410, 330, 90, 30);

        btnActivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Btn_activar.png"))); // NOI18N
        btnActivar.setBorderPainted(false);
        btnActivar.setContentAreaFilled(false);
        btnActivar.setFocusPainted(false);
        btnActivar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Btn_activarhover.png"))); // NOI18N
        btnActivar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Btn_activarhover.png"))); // NOI18N
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });
        Fondo.add(btnActivar);
        btnActivar.setBounds(530, 592, 150, 80);

        getContentPane().add(Fondo);
        Fondo.setBounds(0, 0, 700, 680);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textstatusActionPerformed

    private void textcedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textcedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textcedulaActionPerformed

    private void textnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textnombreActionPerformed

    private void textapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textapellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textapellidoActionPerformed

    private void textcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textcorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textcorreoActionPerformed

    private void texttlfnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texttlfnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texttlfnActionPerformed

    private void comboxgeneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxgeneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxgeneActionPerformed

    private void comboxnivelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxnivelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxnivelActionPerformed

    private void textnomusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textnomusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textnomusuarioActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
// SE REALIZA EL LLAMADO HA EL METODO GUARDAR
guardar();
    }//GEN-LAST:event_guardarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
// SE REALIZA EL LLAMADO HA EL METODO ELIMINAR
eliminar();        
    }//GEN-LAST:event_eliminarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
   // SE REALIZA EL LLAMADO HA EL METODO CONSULTAR
   consultar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
// SE REALIZA EL LLAMADO HA EL METODO MODIFICAR
      modificar();  // TODO add your handling code here:
    }//GEN-LAST:event_modificarActionPerformed

    private void textcedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcedulaKeyPressed
          if(evt.getKeyCode() == evt.VK_ENTER)
            this.textnombre.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textcedula.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textcedulaKeyPressed

    private void textnombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnombreKeyPressed
       if(evt.getKeyCode() == evt.VK_ENTER)
            this.textapellido.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textnombre.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textnombreKeyPressed

    private void textapellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textapellidoKeyPressed
        if(evt.getKeyCode() == evt.VK_ENTER)
            this.textcorreo.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textapellido.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textapellidoKeyPressed

    private void textcorreoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcorreoKeyPressed
          if(evt.getKeyCode() == evt.VK_ENTER)
            this.texttlfn.requestFocus();
          if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textcorreo.getText().equalsIgnoreCase("") && !this.validarCorreo(textcorreo.getText().toCharArray())){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.validarCorreo(textcorreo.getText().toCharArray())){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textcorreoKeyPressed

    private void texttlfnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texttlfnKeyPressed
         if(evt.getKeyCode() == evt.VK_ENTER)
            this.textnomusuario.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.texttlfn.getText().equalsIgnoreCase("") || this.texttlfn.getText().length() != VALIDACION.limitar.telef){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.texttlfn.getText().length() == VALIDACION.limitar.telef){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_texttlfnKeyPressed

    private void comboxgeneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboxgeneKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           comboxnivel.requestFocus();
       }
    }//GEN-LAST:event_comboxgeneKeyPressed

    private void comboxnivelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboxnivelKeyPressed
         if(evt.getKeyCode()== KeyEvent.VK_ENTER){
           textnomusuario.requestFocus();
       }
    }//GEN-LAST:event_comboxnivelKeyPressed

    private void textnomusuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnomusuarioKeyPressed
          if(evt.getKeyCode() == evt.VK_ENTER)
            this.textpass.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox)|| this.textnomusuario.getText().equalsIgnoreCase("")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textnomusuarioKeyPressed

    private void textrepassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textrepassKeyPressed
         if(evt.getKeyCode() == evt.VK_ENTER && this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textrepass.getPassword().length >= 8)
            this.guardar();
        if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textrepass.getPassword().length >= VALIDACION.limitar.tpass){
            this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
        }
        if(String.valueOf(this.textrepass.getPassword()).equalsIgnoreCase("")){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textrepass.getPassword().length < VALIDACION.limitar.tpass){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
    }//GEN-LAST:event_textrepassKeyPressed

    private void textpassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textpassKeyPressed
            if(evt.getKeyCode() == evt.VK_ENTER && this.textpass.getPassword().length >= 8)
            this.textrepass.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || String.valueOf(this.textpass.getPassword()).equalsIgnoreCase("") || this.textpass.getPassword().length < VALIDACION.limitar.tpass){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textpass.getPassword().length >= 8){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textpassKeyPressed

    private void btnverpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverpassActionPerformed
          if(mostrar){

            textvisblepass.setVisible(true);
            textpass.setVisible(false);
            textvisblepass.setText(textpass.getText());
            mostrar=false;

        }else{
            textvisblepass.setVisible(false);
            textpass.setVisible(true);
            textpass.setText(textvisblepass.getText());
            mostrar=true;
        }
    }//GEN-LAST:event_btnverpassActionPerformed

    private void textvisblepassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textvisblepassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textvisblepassActionPerformed

    private void textvisblerepassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textvisblerepassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textvisblerepassActionPerformed

    private void btnverrepassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverrepassActionPerformed
            if(mostrar){

            textvisblerepass.setVisible(true);
            textrepass.setVisible(false);
            textvisblerepass.setText(textrepass.getText());
            mostrar=false;

        }else{
            textvisblerepass.setVisible(false);
            textrepass.setVisible(true);
            textrepass.setText(textvisblerepass.getText());
            mostrar=true;
        }
    }//GEN-LAST:event_btnverrepassActionPerformed

    private void comboxgeneItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxgeneItemStateChanged
        // TODO add your handling code here:
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || String.valueOf(this.comboxgene.getSelectedItem()).equalsIgnoreCase("SELECT")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_comboxgeneItemStateChanged

    private void comboxnivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxnivelItemStateChanged
        // TODO add your handling code here:
         if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || String.valueOf(this.comboxnivel.getSelectedItem()).equalsIgnoreCase("SELECT")){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_comboxnivelItemStateChanged

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

    private void textnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnombreKeyTyped
        // TODO add your handling code here:
        if(textnombre.getText().length() == limitar.nomapell){
            evt.consume();
        }
    }//GEN-LAST:event_textnombreKeyTyped

    private void textapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textapellidoKeyTyped
        // TODO add your handling code here:
        if(textapellido.getText().length() == limitar.nomapell){
            evt.consume();
        }
    }//GEN-LAST:event_textapellidoKeyTyped

    private void textcorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcorreoKeyTyped
        // TODO add your handling code here:
        if(textcorreo.getText().length() == limitar.fulltext){
            evt.consume();
        }
    }//GEN-LAST:event_textcorreoKeyTyped

    private void texttlfnKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texttlfnKeyTyped
        // TODO add your handling code here:
        if(texttlfn.getText().length() == limitar.telef){
            evt.consume();
        }
    }//GEN-LAST:event_texttlfnKeyTyped

    private void textnomusuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textnomusuarioKeyTyped
        // TODO add your handling code here:
        if(textnomusuario.getText().length() == limitar.nomapell){
            evt.consume();
        }
    }//GEN-LAST:event_textnomusuarioKeyTyped

    private void textpassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textpassKeyTyped
        // TODO add your handling code here:
        if(textpass.getText().length() == limitar.fulltext){
            evt.consume();
        }
    }//GEN-LAST:event_textpassKeyTyped

    private void textrepassKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textrepassKeyTyped
        // TODO add your handling code here:
        if(textrepass.getText().length() == limitar.fulltext){
            evt.consume();
        }
        if(evt.getKeyCode() == evt.VK_ENTER && this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textrepass.getPassword().length >= VALIDACION.limitar.tpass && this.texttlfn.getText().length()== VALIDACION.limitar.telef)
            this.guardar();
        if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textrepass.getPassword().length >= VALIDACION.limitar.tpass && this.texttlfn.getText().length()== VALIDACION.limitar.telef){
            this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
        }
        if(String.valueOf(this.textrepass.getPassword()).equalsIgnoreCase("")){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textrepass.getPassword().length < VALIDACION.limitar.tpass){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        
    }//GEN-LAST:event_textrepassKeyTyped

    private void textpassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textpassKeyReleased
        // TODO add your handling code here:
           if(evt.getKeyCode() == evt.VK_ENTER && this.textpass.getPassword().length >= VALIDACION.limitar.tpass)
            this.textrepass.requestFocus();
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || String.valueOf(this.textpass.getPassword()).equalsIgnoreCase("") || this.textpass.getPassword().length < VALIDACION.limitar.tpass || this.texttlfn.getText().length()!= VALIDACION.limitar.telef){
            this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        else if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textpass.getPassword().length >= VALIDACION.limitar.tpass && this.texttlfn.getText().length()== VALIDACION.limitar.telef){
            this.guardar.setEnabled(true);
            this.modificar.setEnabled(true);
            this.eliminar.setEnabled(true);
        }
    }//GEN-LAST:event_textpassKeyReleased

    private void textrepassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textrepassKeyReleased
        // TODO add your handling code here:
         if(evt.getKeyCode() == evt.VK_ENTER && this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textrepass.getPassword().length >= VALIDACION.limitar.tpass && this.texttlfn.getText().length()== VALIDACION.limitar.telef)
            this.guardar();
        if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox) && this.textrepass.getPassword().length >= VALIDACION.limitar.tpass && this.texttlfn.getText().length()== VALIDACION.limitar.telef){
            this.guardar.setEnabled(true);
            this.eliminar.setEnabled(true);
            this.modificar.setEnabled(true);
        }
        if(String.valueOf(this.textrepass.getPassword()).equalsIgnoreCase("")){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
        if(!this.vadid.campos(lcampos) || !this.vadid.combox(lcombox) || this.textrepass.getPassword().length < VALIDACION.limitar.tpass || this.texttlfn.getText().length()!= VALIDACION.limitar.telef){
           this.guardar.setEnabled(false);
            this.eliminar.setEnabled(false);
            this.modificar.setEnabled(false);
        }
    }//GEN-LAST:event_textrepassKeyReleased

    private void textcorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textcorreoKeyReleased
        // TODO add your handling code here:
      
    }//GEN-LAST:event_textcorreoKeyReleased

    private void textpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textpassActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JLabel apellido;
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnverpass;
    private javax.swing.JButton btnverrepass;
    private javax.swing.JLabel calendar;
    private javax.swing.JLabel cedula;
    private javax.swing.JLabel clave;
    private javax.swing.JComboBox<String> comboTipoTelef;
    private javax.swing.JComboBox comboxgene;
    private javax.swing.JComboBox comboxnivel;
    private javax.swing.JLabel correo;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel genero;
    private javax.swing.JButton guardar;
    private javax.swing.JLabel icoapelli;
    private javax.swing.JLabel icocedula;
    private javax.swing.JLabel icoclave;
    private javax.swing.JLabel icocorreo;
    private javax.swing.JLabel icogenero;
    private javax.swing.JLabel iconivel;
    private javax.swing.JLabel iconombre;
    private javax.swing.JLabel icoreclave;
    private javax.swing.JLabel icotlfno;
    private javax.swing.JLabel icousuario;
    private javax.swing.JLabel info;
    private javax.swing.JButton jButton5;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JButton modificar;
    private javax.swing.JLabel nivel;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel nombreusuario;
    private javax.swing.JLabel reclave;
    private javax.swing.JLabel registrousuario;
    private javax.swing.JLabel status;
    private javax.swing.JTextField textapellido;
    private javax.swing.JTextField textcedula;
    private javax.swing.JTextField textcorreo;
    private javax.swing.JTextField textfecha;
    private javax.swing.JTextField textnombre;
    private javax.swing.JTextField textnomusuario;
    private javax.swing.JPasswordField textpass;
    private javax.swing.JPasswordField textrepass;
    private javax.swing.JTextField textstatus;
    private javax.swing.JTextField texttlfn;
    private javax.swing.JTextField textvisblepass;
    private javax.swing.JTextField textvisblerepass;
    private javax.swing.JLabel tlfno;
    private javax.swing.JLabel tlfno1;
    // End of variables declaration//GEN-END:variables

 //METODO QUE PERMITE VACIAR TODAS LAS CASILLAS DE TEXTOS   

    private void limpiar() {
 //OBTENER FECHA DE LA PC
        
    
    textfecha.setText(fec);    
    textcedula.setText("")      ; 
    textnombre.setText("")  ;
    textapellido.setText("")  ;
    textcorreo.setText("");
    texttlfn.setText("")  ;
    textstatus.setText("")  ;

    textnomusuario.setText("") ;
    textpass.setText("");                      
    textrepass.setText("");
    this.comboTipoTelef.setSelectedItem("SELECCIONE");
    this.comboxgene.setSelectedItem("SELECCIONE");
    this.comboxnivel.setSelectedItem("SELECCIONE");

    
    this.guardar.setEnabled(false);
    this.eliminar.setEnabled(false);
    this.modificar.setEnabled(false);
    
    
    }

 //METODO QUE ME PERMITE GUARDAR LOS NUEVOS REGISTROS DE USUARIOS A LA BASE DE DATOS   
    private void guardar() {

  String ci =textcedula.getText()      ; 


//PASANDO EL TEXTO DE LOS CUADROS DE TEXTOS A VARIABLES
String  fechaR  = this.fec   ;
String  nombre  =    textnombre.getText()  ;
String  apellido=    textapellido.getText()  ;
String  correo   =   textcorreo.getText();
String  telefono =   texttlfn.getText()  ;
String  genero   = String.valueOf(comboxgene.getSelectedItem());
String  nivel    = String.valueOf(comboxnivel.getSelectedItem());
String  nombreU  =   textnomusuario.getText() ;
String  clave    =   String.valueOf(textpass.getPassword());                      
String  clave2    =   String.valueOf(textrepass.getPassword());   

String  status="1"      ;                      
String sSQL="";
String mensaje ="";       

          if(vadid.cmpr_contrasenas(textpass.getPassword(), textrepass.getPassword())){
    
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
               switch(String.valueOf(this.comboxgene.getSelectedItem())){
                   case "FEMENINO": genero = "F"; break;
                   case "MASCULINO": genero = "M"; break;
               }
               
               switch(String.valueOf(this.comboxnivel.getSelectedItem())){
                   case "ADMINISTRADOR" : nivel = "1"; break;
                   case "CAJERO" : nivel = "2"; break;
                   case "INVENTARIO": nivel = "3"; break;
               }
               
               String tipo_telef = "";
               switch(String.valueOf(this.comboTipoTelef.getSelectedItem())){
                   case "Movil" : tipo_telef = "1"; break;
                   case "Casa"  : tipo_telef = "2"; break;
               }
   // SENTENCIA SQL PARA GUARDAR LOS DATOS DE LOS USUARIOS
            this.bd.setStm("INSERT INTO empleados(cedula, nombres, apellidos, correo, telefono, tipo_telefono, genero, status)" + 
                    " VALUES ('" +  ci +  "', '" + nombre + "', '" + apellido + "', '" + correo + "', '" + telefono + "', '" + tipo_telef + "', '" + genero + "', '1')");
            clave = Encriptacion.Encriptar(clave);
            this.bd.setStm("INSERT INTO usuarios(cedula_empleado, nombre_usuario, contrasena, cod_nivel, status)" + 
                    " VALUES ('" + ci + "', '" + nombreU + "', '" + clave + "', '" + nivel + "', '" + status +"')");
            mensaje = "SE HA GUARDADO DE MANERA CORRECTA";
              JOptionPane.showMessageDialog(null, mensaje);
               BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Datos guardados de manera correcta");

                limpiar(); 
                new ConfigurarPreguntas(ci, this.bd).setVisible(true);
                //this.bd.Close();
                } catch (SQLException ex) {
            Logger.getLogger(cajeros.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(this," YA EXISTE UN USUARIO CON DICHA CEDULA O NOMBRE DE USUARIO","ERROR",JOptionPane.WARNING_MESSAGE);
              BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al guardar en la base de datos:{0}", ex.getMessage());

                }
               }}    else{
                 JOptionPane.showMessageDialog(this,"LAS CLAVES SON DISTINTAS","ERROR",JOptionPane.WARNING_MESSAGE);
                 BitacoraVentanas.LOG_RAIZ.log(Level.WARNING, "Error en las contraseñas");

          }
  
    }
// METODO QUE PERMITE REALIZAR UNA ELIMINACION LOGICA DE LOS REGISTROS DE  USUARIOS
    private void eliminar() {

       String mensaje="";
      // MENSAJE DE CONFIRMACION PARA ELIMINACION LOGICA DE LOS REGISTROS DE  USUARIOS
       
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
        
        
      // SENTENCIA SQL PARA ELIMINACION LOGICA DE LOS REGISTROS DE  USUARIOS
            
               
            this.bd.setStm("UPDATE usuarios SET status='"+status+"'  WHERE cedula_empleado= '"+ci+"'");
            mensaje = "SE HA ELIMINADO";
              JOptionPane.showMessageDialog(null, mensaje);
                                 limpiar();
              BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Modificacion de datos exitosos");

                                 //this.bd.Close();
    
                } catch (SQLException ex) {
                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al modificar en la base de datos:{0}", ex.getMessage());
            }
     
        }
             if (opcion == JOptionPane.NO_OPTION){
                   JOptionPane.showMessageDialog(this,"NO SE ELIMINO","AVISO",JOptionPane.INFORMATION_MESSAGE);  
              }
 
    
    }
//METODO QUE PERMITE REALIZAR CONSULTAS SQL Y MOSTRARLAS EN LA INTERFAZ DE USUARIOS
    private void consultar() {

      try{
            new ConsultaCajero(this, true, bd, this, this.nomb_user, this.cod_user).setVisible(true);
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(this, "Error al conectar!!", "Error", JOptionPane.ERROR_MESSAGE);
           BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al conectar con la base de datos:{0}", ex.getMessage());

            return;
        }
    
    
    
    }
// METODO QUE PERMITE REALIZAR UNA MODIFICACION EN LOS REGISTROS DE USUARIOS QUE EXISTAN EN LA BASE DE DATOS
    private void modificar() {
  String  nombre  =    textnombre.getText()  ;
String  apellido=    textapellido.getText()  ;
String  correo   =   textcorreo.getText();
String  telefono =   texttlfn.getText()  ;
String  genero   = String.valueOf(comboxgene.getSelectedItem());
String  nivel    = String.valueOf(comboxnivel.getSelectedItem());
String  nombreU  =   textnomusuario.getText() ;
String  clave    =   textpass.getText();                      
String  clave2    =   textrepass.getText();   

String  status="1"      ;                      
String sSQL="";
String mensaje ="";       

          if(vadid.cmpr_contrasenas(textpass.getPassword(), textrepass.getPassword())){
    
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
           String tipo_telef = "";
           switch(String.valueOf(this.comboTipoTelef.getSelectedItem())){
               case "Movil" : tipo_telef = "1"; break;
               case "Casa"  : tipo_telef = "2"; break;
           }
     // SENTENCIA SQL QUE PERMITE MODIFICAR LOS DATOS
   
    this.bd.setStm("UPDATE empleados SET nombres='" + nombre + "', apellidos='" + apellido + 
            ", correo= '" + correo + "', telefono= '" + telefono + "', tipo_telefono = '" + tipo_telef + ",  genero ='"+ genero + "' WHERE cedula='"+ this.textcedula.getText() + "'" );
    
    this.bd.setStm("UPDATE usuarios SET nombre_usuario='" + this.textnomusuario.getText() + "', contrasena='" + Encriptacion.Encriptar(String.valueOf(this.textpass.getPassword()) +
            "', cod_nivel ='" + String.valueOf(this.comboxnivel.getSelectedItem())+ "'"));
                              JOptionPane.showMessageDialog(null, "MODIFICADO");
                                    limpiar();
        BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Datos modificados con exito");

                                    //this.bd.Close();

    } catch (SQLException ex) {

                BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al modificar datos en la base de datos:{0}", ex.getMessage());

    }  }}    else{
                 JOptionPane.showMessageDialog(this,"LAS CLAVES SON DISTINTAS","ERROR",JOptionPane.WARNING_MESSAGE);
                BitacoraVentanas.LOG_RAIZ.log(Level.WARNING, "Error en las contraseñas");

          }
  

    
    
    }

    private void cargarCombos() {
        try{
            this.bd.Conectar();
            this.bd.setResult("SELECT nombre FROM tipo_telefono");
            ResultSet result = this.bd.getResult();
            while(result.next()){
                this.comboTipoTelef.addItem(result.getString(1));
            }
            
            this.bd.setResult("SELECT nombre FROM tipos_usuarios");
            result = this.bd.getResult();
            while(result.next()){
                this.comboxnivel.addItem(result.getString(1));
            }
        }catch(SQLException ex){
          BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al cargar datos en combobox:{0}", ex.getMessage());

        }
    }

    private void activar() {
       if(this.vadid.campos(lcampos) && this.vadid.combox(lcombox)){
           try{
               this.bd.Conectar();
               this.bd.setStm("UPDATE empleados  SET status = '1' WHERE  cedula = '" + this.textcedula.getText() + "'");
               this.bd.setStm("UPDATE usuarios SET status = '1'  WHERE cedula_empleado = '" + this.textcedula.getText() + "'");
               BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "Usuario activado con exito:{0}");

               
           } catch (SQLException ex){
              BitacoraVentanas.LOG_RAIZ.log(Level.CONFIG, "Error al intentar activar un usuario:{0}", ex.getMessage());

           }
       } else {
           JOptionPane.showMessageDialog(this, "Debe llenar todos los campos!!!", "ERROR", JOptionPane.ERROR_MESSAGE);
           BitacoraVentanas.LOG_RAIZ.log(Level.WARNING, "Error al llenar campos en la ventana");

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
