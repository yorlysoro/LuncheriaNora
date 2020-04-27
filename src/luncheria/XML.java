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
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 *
 * @author Jerrinson
 */
public class XML {
    
    private static String obtenerNodoValor(String strTag, Element eBitacora){
        Node nValor = (Node)eBitacora.getElementsByTagName(strTag).item(0).getFirstChild();
        return nValor.getNodeValue();
    }
    
    public ArrayList<Bitacora> obtenerBitacoras() {
        ArrayList<Bitacora> listaBitacoras = new ArrayList<Bitacora>();
        try{
             DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
             docFactory.setValidating(false);
             DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
             docBuilder.setEntityResolver(  new EntityResolver() {
              @Override
              public InputSource resolveEntity(String publicId, String systemId)
                throws SAXException, IOException {
                if (systemId.contains("logger.dtd")) {
                    return new InputSource(new StringReader(""));
                } else {
                    return null;
                }
              }
            });
             Document doc = docBuilder.parse(new File("bitacora.xml"));
             
             doc.getDocumentElement().normalize();
             

             
             NodeList nodosBitacora = doc.getElementsByTagName("record");
             
             for(int i = 0; i < nodosBitacora.getLength(); i++){
                 Node bitacora = nodosBitacora.item(i);
                 if(bitacora.getNodeType() == Node.ELEMENT_NODE){
                     Element unElement = (Element) bitacora;
                     Bitacora objBitacora = new Bitacora();
                     objBitacora.setDate(obtenerNodoValor("date", unElement));
                     objBitacora.setMillis(obtenerNodoValor("millis", unElement));
                     objBitacora.setSequence(obtenerNodoValor("sequence", unElement));
                     objBitacora.setLogger(obtenerNodoValor("logger", unElement));
                     objBitacora.setLevel(obtenerNodoValor("level", unElement));
                     objBitacora.setClasse(obtenerNodoValor("class", unElement));
                     objBitacora.setMethod(obtenerNodoValor("method", unElement));
                     objBitacora.setThread(obtenerNodoValor("thread", unElement));
                     objBitacora.setMessage(obtenerNodoValor("message", unElement));
                     listaBitacoras.add(objBitacora);
                }
             }
        }catch(ParserConfigurationException ParseEx){
            System.err.println("Error ParseEx: " + ParseEx.getMessage());
        } catch(SAXException saxE){
            System.err.println("Errorn saxE: " + saxE.getMessage());
        } catch(IOException IOex){
            System.err.println("Error IOex: " + IOex.getMessage());
        }
        return listaBitacoras;
    }
    
    
    
}
