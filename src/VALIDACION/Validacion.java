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
package VALIDACION;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import luncheria.basedatos;
import luncheria.cajeros;

/**
 *
 * @author Jerrinson
 */
public class Validacion {
    
    public boolean campos(JTextField[] lcampos){
       for(int i = 0; i < lcampos.length; i++){
           if(lcampos[i].getText().equalsIgnoreCase("")){
               return false;
           }
       }
       return true;
    }
    
    public boolean combox(JComboBox[] lcombox){
        for(int i = 0; i < lcombox.length; i++){
            if(String.valueOf(lcombox[i].getSelectedItem()).equals("SELECCIONE")){
                return false;
                
            }
        }
        return true;
    }
    
    public boolean cmpr_contrasenas(char[] pass1, char[] pass2){
        if(pass1.length == pass2.length){
        for(int i = 0; i < pass1.length; i++){
            if(pass1[i] != pass2[i]){
                return false;
            }
        }
        }else {
            return false;
        }
        return true;
    }

      public void SNumeros (JTextField a)
    {
        a.addKeyListener(new KeyAdapter()
        {
            public void keyTyped( KeyEvent e)
            {
                char c=e. getKeyChar();
                if(!Character.isDigit(c))
                {
                    //getToolkit().beep();
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
                    //getToolkit().beep();
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
                    //getToolkit().beep();
                    e.consume();
                }
            }

           
        });
    }
      public boolean validarCorreo(char[] correo) {
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
    
}
