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

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author yorlysoro <yorlysoro@gmail.com>
 */
public class Encriptacion {
    public static String Desencriptar(String textoencriptado){
        String secretKey = "LuncheriaNora";
        String base64EncryptedString = "";
        try{
            byte[] message = org.apache.commons.codec.binary.Base64.decodeBase64(textoencriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] KeyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(KeyBytes, "DESede");
            Cipher descipher = Cipher.getInstance("DESede");
            descipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = descipher.doFinal(message);
            base64EncryptedString = new String(plainText, "utf-8");
            
            
        }catch (Exception ex){
            
        }
        return base64EncryptedString;
    }

    public static String Encriptar(String texto) {
        String secretKey = "LuncheriaNora";
        String base64EncryptedString = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] KeyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(KeyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = org.apache.commons.codec.binary.Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
            
            
        }catch (Exception ex){
            
        }
        return base64EncryptedString;
    }
}
