/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Module;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class TestReadWrite {
    public static void main(String [] args) throws IOException{
        byte [] data ;
        Path path = Paths.get("pic.jpg");
            data = Files.readAllBytes(path);
            System.out.println(data);
        try {
            FileOutputStream fos = new FileOutputStream("pic1.jpg");
            fos.write(data);
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
