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

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Clase BitacoraVentanas
 * Esta clase se encargar de crear la bitacora del sistema por medio del uso de la clase Logger,
 * crea el archivo de bitacora y se le asignan todas las clases que se les desea hacer un seguimiento
 * @author Jerrinson
 */
public class BitacoraVentanas {
    public final static Logger LOG_RAIZ = Logger.getLogger("luncheria");
    public static final Logger Luncheria = Logger.getLogger("luncheria.Luncheria");
    public static final Logger Loggin = Logger.getLogger("luncheria.New_A");
    public static final Logger vtnAgregarProductos = Logger.getLogger("luncheria.AgregarProductos");
    public static final Logger vtnMenu = Logger.getLogger("luncheria.New_menu");
    public static final Logger vtnCajeros = Logger.getLogger("luncherio.cajeros");
    public static final Logger vtnClientes = Logger.getLogger("luncheria.clientes");
    public static final Logger vtnFactura = Logger.getLogger("luncheria.factura");
    public static final Logger vtnInventario = Logger.getLogger("luncheria.inventario");
    public static final Logger vtnReportes = Logger.getLogger("luncheria.reportes");
    public static final Logger vtnServicios = Logger.getLogger("luncheria.servicio");
    public static final Logger vtnUsuarios = Logger.getLogger("luncheria.usuarios");
    public static final Logger vtnClientes2 = Logger.getLogger("luncheria.vtnclientes");
    public static final Logger vtnConfiguracion = Logger.getLogger("luncheria.Configuracion");
    public static final Logger vtnConsultaCajero = Logger.getLogger("luncheria.ConsultaCajero");
    public static final Logger vtnConsultaCliente = Logger.getLogger("luncheria.ConsultaCliente");
    public static final Logger vtnConsultaFactCliente = Logger.getLogger("luncheria.ConsultaFactCliente");
    public static final Logger vtnConsultaFactura = Logger.getLogger("luncheria.ConsultaFactura");
    public static final Logger vtnConsultaServicio = Logger.getLogger("luncheria.ConsultaServicio");
    
    
    
    public static void Cargar_Bitacora(){
        try {
        Handler consoleHandler = new ConsoleHandler();
        Handler fileHandler = new FileHandler("./bitacora.xml", false);
        LOG_RAIZ.addHandler(consoleHandler);
        LOG_RAIZ.addHandler(fileHandler);
        } catch (IOException ex) {
            LOG_RAIZ.log(Level.SEVERE, "Error de IO");
        } catch (SecurityException ex) {
            LOG_RAIZ.log(Level.SEVERE, "Error de Seguridad");
        }

    }








}
