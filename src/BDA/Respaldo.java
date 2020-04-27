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
package BDA;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author yorlysoro <yorlysoro@gmail.com>
 */
public class Respaldo {
    private String Archivo;
    
    public Respaldo(String Archivo){
       this.Archivo = Archivo;
    }
    public void Respaldar() {
        try {
            Process p = Runtime.getRuntime().exec("mysql/bin/mysqldump -u root Luncheria");
            new LogRespaldo(p.getErrorStream()).start();
            InputStream is = p.getInputStream();
            FileOutputStream fos = new FileOutputStream(this.Archivo);
            byte[] buffer = new byte[2048];
            int leido = is.read(buffer);
            while (leido > 0) {
                fos.write(buffer, 0, leido);
                leido = is.read(buffer);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Restuarar() {
        try {
            String comand = "mysql/bin/mysql -u root -e\"CREATE DATABASE IF NOT EXISTS Luncheria;\"";
            Process cdb = Runtime.getRuntime().exec(comand);
            new LogRespaldo(cdb.getErrorStream()).start();
            Process p = Runtime.getRuntime().exec("mysql/bin/mysql -u root Luncheria");
            new LogRespaldo(p.getErrorStream()).start();
            OutputStream os = p.getOutputStream();
            FileInputStream fis = new FileInputStream(this.Archivo);
            byte[] buffer = new byte[2048];
            int leido = fis.read(buffer);
            while (leido > 0) {
                os.write(buffer, 0, leido);
                leido = fis.read(buffer);
            }
            os.flush();
            os.close();
            fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
}
