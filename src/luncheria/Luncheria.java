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

import BDA.RespaldoAutomatico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;


public class Luncheria {

    /**
     * @param args the command line arguments
     */
    //CLASE PRINCIPAL DEL SISTEMA DONDE SE LE REALIZA EL LLAMADO A LA INTERFAZ DE ACCESO

    public static void main(String[] args) {
        String nombre_user = null;
        String cod_nivel = null;
              GuardarBitacora objGB = new GuardarBitacora();
              BitacoraVentanas.Cargar_Bitacora();
              BitacoraVentanas.LOG_RAIZ.log(Level.INFO, "INICIANDO EL SISTEMA");
              iniciar_respaldo();
              new New_A().setVisible(true);
              iniciar_respaldo();
              //basedatos bd = new basedatos();
              //new cajeros(bd,"yorlys", "1").setVisible(true);
               // new usuarios(bd).setVisible(true);   
               //new New_menu(nombre_user, cod_nivel, bd).setVisible(true);
                 //new menu(nombre_user, cod_nivel, bd).setVisible(true);
               //new cajeros(bd).setVisible(true);
    }

    private static void iniciar_respaldo() {
        RespaldoAutomatico RespaldoAuto = new RespaldoAutomatico();
        Timer temporizador = new Timer();
        temporizador.scheduleAtFixedRate(RespaldoAuto, 1, 7000);
    }
}