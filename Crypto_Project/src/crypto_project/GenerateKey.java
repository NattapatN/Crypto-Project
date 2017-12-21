/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto_project;

import Module.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;
import java.util.Random;
/**
 *
 * @author NattapatN
 */
public class GenerateKey {
    String fileName;
    PrintWriter writePublic ;
    PrintWriter writePrivate ;
    int p,g,y,u;
    Random ran =new Random();
    FastExpo fExpo = new FastExpo();
    public GenerateKey(){}
    
    public boolean genKey (String fName,int b){
        //read key from file
        GetKeyFile gkff = new GetKeyFile();
        p = gkff.getKey(fName,b);
        
        //get Real (safe) prime
        p =getPrime(p);
        
        //find g
        int temp;
        g = ran.nextInt(p-1)+1;
        temp = fExpo.getFExpo(g,(p-1)/2,p);
        //check g
        if(temp==1)g=p-g;
        
        //random U
        u = ran.nextInt(p-1)+1;
        
        //create y
        y=fExpo.getFExpo(g, u, p);
        System.out.println("------------------------------------");
        System.out.println("Public key is : ("+p+", "+g+", "+y+")");
        System.out.println("Private key is : "+u);
        
        //write file
        System.out.println("------------------------------------");
        if(writeFile())System.out.println("[Write Key to File Complete!!]");
        
        return true;
    }
    private int getPrime(int inn){
        LehmahTest lTest = new LehmahTest();
        int in=inn;
        boolean found =false;
        
        //Check odd
        if(in%2==0) in++;
        
        //test prime 
        while (!found) {
            if (lTest.isPrime(in)) {
                if (lTest.isPrime((in - 1) / 2)) {
                    found = true;
                } else {
                    in += 2;
                }
            } else {
                in += 2;
            }
        }
        
        return in;
    }
    
    private boolean writeFile(){
        
        try {
            //define
            writePublic = new PrintWriter("PublicKey.txt");
            writePublic.println(p);
            writePublic.println(g);
            writePublic.println(y);
            writePublic.close();
            writePrivate = new PrintWriter("PrivateKey.txt");
            writePrivate.println(u);
            writePrivate.println(p);
            writePrivate.close();
            
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GenerateKey.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
