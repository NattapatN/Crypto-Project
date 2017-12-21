/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.PrintWriter;
import java.util.Random;
import Module.*;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class GenerateKey {
    String fileName;
    PrintWriter writePublic ;
    PrintWriter writePrivate ;
    FastExponential fExpo = new FastExponential();
    
    Random ran = new Random();
    int g,p,u,y;
    public GenerateKey(){}
    
    public boolean genKey (String fName,int b){
        //read key from file
        GetKeyFile gkff = new GetKeyFile();
        p = gkff.getKey(fName,b);
        
        //find Real (Safe) Prime
        p = findPrime(p);
        
        //gen G
        int temp;
        g = ran.nextInt(p-1)+1;
        temp = fExpo.getFastExpo(g,(p-1)/2,p);
        //check g
        if(temp==1)g=p-g;
        
        //Random u
        u = ran.nextInt(p-1)+1;
        
        //create y
        y=fExpo.getFastExpo(g, u, p);
        System.out.println("------------------------------------");
        System.out.println("Public key is : ("+p+", "+g+", "+y+")");
        System.out.println("Private key is : "+u);
        
        //write file
        System.out.println("------------------------------------");
        if(writeFile())System.out.println("[Write Key to File Complete!!]");
        
        return true;
    }
    
    private int findPrime(int test){
        //define
        int in = test;
        boolean found = false;
        LehmahTest lTest = new LehmahTest();
        
        //check odd
        if(in%2==0) in++;
        
        //Fine Prime
        while(!found){
            if(lTest.isPrime(in)){
                if (lTest.isPrime((in - 1) / 2)) {
                    found=true;
                }
                else{
                    in += 2;
                }
            }
            else{
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
