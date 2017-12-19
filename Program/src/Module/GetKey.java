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
public class GetKey {
    BufferedReader br ;
    FileReader fr ;
    String [] key;
    public GetKey(String fileName) {
        try {
            key = new String[3];
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(fileName);
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
    public String getA(){return key[0];}
    public String getB(){return key[1];}
    public String getC(){return key[2];}
}
