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
import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


// CLASE DISEÑADA PARA CREAR LA CONEXION A LA BASE DE DATOS
public class basedatos{
  //DATOS DE LA BASE DE DATOS
    private ResultSet Result;
    private Statement stm;
    private String db;
    private String url = "jdbc:mysql://";
    private String user;
    private String pass;
    private String sent;
    private Connection con;
    private String port;
    private String host;
    private Scanner entrada;
 public basedatos()
    {
        
    }

    public ResultSet getResult() {
        return Result;
    }

    public void setResult(String Query) throws SQLException {
        this.Result = this.stm.executeQuery(Query);
    }

    public void setStm(String Query) throws SQLException {
        this.stm.execute(Query);
    }

    public Statement getStm() {
        return stm;
    }
    
    public void Close() throws SQLException{
        this.con.close();
    }

    public Connection getCon() {
        return con;
    }
    
    
    //CLASE QUE PERMITE CONECTARSE HA LA BASE DE DATOS
    public  Connection Conectar()
    {
        Connection link = null;
        Limpiar();
        Leer();
        try
        {
            
            Class.forName("org.gjt.mm.mysql.Driver");
            this.url += this.host + "/" + this.db;
            System.out.println(this.url);
            link = DriverManager.getConnection(this.url, this.user, this.pass);
            stm=link.createStatement();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        } 
        
        this.con = link;
        try {
            this.con.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(basedatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return link;
    }

    private void Leer(){
        try{
            entrada = new Scanner(new File("config.txt"));
        }catch (FileNotFoundException fileNotFound){
            System.err.println("Error al leer la configuracion de la base de datos");
        }
        
        while(entrada.hasNext()){
            db = entrada.next();
            host = entrada.next();
            port = entrada.next();
            user = entrada.next();
            try{
                    pass =entrada.next();
                }catch(NoSuchElementException noElement){
                    pass = "";
                }
        }
        
        entrada.close();
    }

    private void Limpiar() {
        this.url = "jdbc:mysql://";
        this.port = "";
        this.user = "";
        this.host = "";
        this.db = "";
        this.pass = "";    }

 


}

