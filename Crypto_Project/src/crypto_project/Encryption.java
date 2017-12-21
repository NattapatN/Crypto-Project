/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto_project;

import Module.*;
import java.io.PrintWriter;
import java.util.Random;


/**
 *
 * @author NattapatN
 */
public class Encryption {
    static String filename;
    static PrintWriter writeCipher;
    Random ran = new Random();
    static int p,g,y;
    static int k;
    static int block;
    static String cipherA="",cipherB="";
    
    public Encryption(){
        ReadKey getKey = new ReadKey("PublicKey.txt");
        p=Integer.parseInt(getKey.getA());
        g=Integer.parseInt(getKey.getB());
        y=Integer.parseInt(getKey.getC());
    }
    
    public boolean encrypt(String file,int b){
        filename =file;
        
        //find k
        k = getK();
        
        
        
        
        return true;
    }
    
    private int getK(){
        Random ran = new Random();
        ExtendedEuclid exGCD =new ExtendedEuclid();
        int n;
        do{
            n=ran.nextInt(p-1)+1;
        }while(exGCD.getGCD(p-1, n));
        
        return n;
    }
}
