/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class WriteByte {
    
    FileOutputStream dop;
    Path path ;
    byte data[];
    
    public WriteByte(){}
    
    public boolean writeFile(String file,String filename){ 
        data = convertBittoByte(file);
        try {
            dop = new FileOutputStream(filename);
            dop.write(data);
            dop.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WriteByte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WriteByte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    private byte[] convertBittoByte(String in){
        short a = Short.parseShort(in, 2);
        ByteBuffer bytes = ByteBuffer.allocate(2).putShort(a);
        
        byte[] temp=bytes.array();
        return temp;
    }
    
}
