/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nattapat
 */
public class ReadBitFile {
    public ReadBitFile(){}
    
    public String readBit(String file){
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file))) {
            for (int b; (b = is.read()) != -1;) {
                int mask;
                for (int i = 7; i >= 0; i--) {
                    mask = 1 << i;
                    sb.append((b & mask) != 0 ? "1" : "0");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ReadBitFile.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }
}
