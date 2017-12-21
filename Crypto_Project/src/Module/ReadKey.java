/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author NattapatN
 */
public class ReadKey {
    BufferedReader br ;
    FileReader fr ;
    String [] key;
    
    public ReadKey(String file){
        try {
            key = new String[3];
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String sCurrentLine;
            int i =0;
            while ((sCurrentLine = br.readLine()) != null) {
                key[i]=sCurrentLine;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {br.close();}
                if (fr != null) {fr.close();}
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public String getA(){return null;}
    public String getB(){return null;}
    public String getC(){return null;}
    
    
}
