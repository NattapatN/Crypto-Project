/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import program.InFiletoBit;

/**
 *
 * @author NattapatN
 */
public class FileToBit {
    public FileToBit(){}
    
    public StringBuilder toBit(String filename){
        StringBuilder sb = new StringBuilder();
        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(filename))) {
            for (int b; (b = is.read()) != -1;) {
                int mask;
                for (int i = 7; i >= 0; i--) {
                    mask = 1 << i;
                    sb.append((b & mask) != 0 ? "1" : "0");
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InFiletoBit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InFiletoBit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sb;
    }
    
}
