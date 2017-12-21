/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Module.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author NattapatN
 */
public class Decryption {
    int u,p,block;
    Padding pad = new Padding();
    static DecToBinary d2b = new DecToBinary();
    static ExtendedEuclidGCD exGCD = new ExtendedEuclidGCD();
    FastExponential fExpo = new FastExponential();
    Padding padd = new Padding();
    String cipherA,cipherB;
    program.BitToText b2t = new program.BitToText();
    String plain="";
    static PrintWriter writePlain;
    
    public Decryption(){
        GetKey getKey = new GetKey("PrivateKey.txt");
        u=Integer.parseInt(getKey.getA());
        p=Integer.parseInt(getKey.getB());
    }
    
    public boolean Decrypt(int b){
        block=b;
        GetKey getCipherA = new GetKey("test.txt");
        GetKey getCipherB = new GetKey("CipherB.txt");
        cipherA=getCipherA.getA()+"0";
        cipherB=getCipherA.getB()+"0";
//        System.out.println(cipherA);
//        System.out.println(cipherB);
        
        //decrypt
        int ta,tb;
        while(cipherA.length()>b){
            ta = Integer.parseInt(cipherA.substring(0,b),2);
            tb = Integer.parseInt(cipherB.substring(0,b),2);
            cipherA = cipherA.substring(b);
            cipherB = cipherB.substring(b);
            decrypt(ta,tb);
        }
        //unpad
        //plain = padd.UnPad(plain);
        
        //convert to text
        
        System.out.println(plain);
        plain = b2t.getText(plain);
        
        //write file
        if(writeFile())System.out.println("[Write Message to File Complete!!]");
        
        return true;
    }
    
    private void decrypt(int ain,int bin){
        int a=ain;
        int b= bin;
        long x,invertA;
        a=fExpo.getFastExpo(a,u,p);
        //System.out.println("a : "+a);
        boolean some = exGCD.getGCD(a, p);
        invertA=exGCD.getA2();
        //System.out.println("Invert A : "+invertA);
        x=Math.floorMod((long)b*invertA, p);
        String prefill = d2b.convertToBi((int)x);
        while(prefill.length()<block)prefill = "0"+prefill;
        plain = plain+prefill;
    }
    
    private boolean writeFile(){
        try {
            writePlain = new PrintWriter("Plaintext.txt");
            writePlain .println(plain);
            writePlain .close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Decryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
