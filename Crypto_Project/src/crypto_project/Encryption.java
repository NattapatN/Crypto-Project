/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto_project;

import Module.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author NattapatN
 */
public class Encryption {
    static String filename;
    static PrintWriter writeCipher;
    DectoBi d2b = new DectoBi();
    ReadBitFile rbf =new ReadBitFile();
    Random ran = new Random(); 
    FastExpo fExpo = new FastExpo();
    static int p,g,y;
    static int k;
    static int block;
    Padding padd = new Padding();
    static String cipherA="",cipherB="";
    
    public Encryption(){
        ReadKey getKey = new ReadKey("PublicKey.txt");
        p=Integer.parseInt(getKey.getA());
        g=Integer.parseInt(getKey.getB());
        y=Integer.parseInt(getKey.getC());
    }
    
    public boolean encrypt(String file,int b,String fileOut){
        filename =file;
        block = b;
        String plain;
        
        //find k
        k = getK();
        
        //read plaintext
        plain = rbf.readBit(file);
        
        //Padding
        plain = padd.fillPad(plain,b);
        
        //encrypt
        String temp;
        while(plain.length()>b){
            temp = plain.substring(0,b);
            goEncrypt(Integer.parseInt(temp, 2));
            plain = plain.substring(b);
        }
        
        //write file 
        if(writetoFile(fileOut))System.out.println("Encryption Complete!!");
        
        
        
        return true;
    }
    
    private int getK(){
        Random ran = new Random();
        ExtendedEuclid exGCD =new ExtendedEuclid();
        int n;
        do{
            n=ran.nextInt(p-1)+1;
        }while(!exGCD.getGCD(p-1, n));
        
        return n;
    }
    
    private void goEncrypt(int in){
        int a,b;
        long temp;
        a= fExpo.getFExpo(g, k, p);
        b=fExpo.getFExpo(y, k, p);
        temp = Math.floorMod(b*in, p);
        
        cipherA = cipherA+d2b.getBinary(a, block);
        cipherB = cipherB+d2b.getBinary((int)temp, block);
    }
    
    private boolean writetoFile(String toFile){
        try {
            writeCipher = new PrintWriter(toFile);
            writeCipher.println(cipherA);
            writeCipher.println(cipherB);
            writeCipher.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
