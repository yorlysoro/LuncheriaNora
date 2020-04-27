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

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 *
 * @author Jerrinson
 */
public class GuardarBitacora {
    private ArrayList<Bitacora> listBitacoras;
    private XML objxml;
    private String cod_user;
    private basedatos bd;
    private Scanner entrada;
    
    public GuardarBitacora(){
      
        this.objxml = new XML();
        try{
            entrada = new Scanner(new File("cod_user.txt"));
        }catch( FileNotFoundException fileNotFound){
          JOptionPane.showMessageDialog(null, "Error al cargar la configuracion", "Error", JOptionPane.ERROR_MESSAGE);
           BitacoraVentanas.LOG_RAIZ.log(Level.SEVERE, "Error en lectura de configuracion: {0}", fileNotFound.getMessage());


        }
        
        while(entrada.hasNext()){
                this.cod_user = entrada.next();
        }
        entrada.close();
        this.listBitacoras = objxml.obtenerBitacoras();
        this.bd = new basedatos();
        this.guardar();
    }

    private void guardar() {
        System.out.println(this.cod_user);
        try {
            for(int i = 0; i < listBitacoras.size(); i++){
                this.bd.Conectar();
                this.bd.setStm("INSERT INTO actividad_usuarios(cod, cod_usuario, Accion, Fecha_Hora, ventana, mensaje) "
                + " VALUES( '" + this.listBitacoras.get(i).getMillis() + "', '" + this.cod_user + "', '" + this.verificarAccion(this.listBitacoras.get(i).getLevel()) + "', '"
                + this.verificarFecha(this.listBitacoras.get(i).getDate().toCharArray()) + "', '" + this.listBitacoras.get(i).getClasse() + "', '"  + this.listBitacoras.get(i).getMessage() + "' )");
            }
        }catch (SQLException ex){
            System.out.println("ERROOOOOOOOOOOOOOR");
            System.err.println(ex.getMessage());
        }
    }

    private String verificarAccion(String level) {
        switch(level){
            case "SEVERE" : return "1";
            case "WARNING" : return "2";
            case "INFO" : return "3";
            case "CONFIG" : return "4";
            case "FINE" : return "5";
            case "FINER" : return "6";
            case "FINEST" : return "7";
        }
        return "1";
    }

    private String verificarFecha(char date[]) {
        char new_date[] = new char[date.length];
        for(int i = 0; i < date.length; i++){
            if(date[i] == 'T' || date[i] == 't')
                new_date[i] = ' ';
            else
                new_date[i] = date[i];
        }
        return String.valueOf(new_date);
        
    }
}
