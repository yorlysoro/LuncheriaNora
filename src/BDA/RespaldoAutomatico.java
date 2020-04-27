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

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimerTask;

/**
 *
 * @author yorlysoro <yorlysoro@gmail.com>
 */
public class RespaldoAutomatico extends TimerTask{
    @Override
    public void run(){
            try {
                Process p = Runtime.getRuntime().exec("mysql/bin/mysqldump -u root Luncheri");
                new LogRespaldo(p.getErrorStream()).start();
                InputStream is = p.getInputStream();
                Calendar cal = Calendar.getInstance();
                String fec = cal.get(Calendar.DATE) + cal.get(Calendar.MONTH) + cal.get(Calendar.YEAR)+ cal.get(Calendar.HOUR_OF_DAY)+":"+ cal.get(Calendar.MINUTE)+":"+ cal.get(Calendar.SECOND)+":"+ cal.get(Calendar.MILLISECOND);
                String Archivo = "backups/backup"+fec+".sql";
                FileOutputStream fos = new FileOutputStream(Archivo);
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
    
   
     
}
