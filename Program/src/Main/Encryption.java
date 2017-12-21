/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import Module.*;
import java.util.Random;
/**
 *
 * @author NattapatN
 */
public class Encryption {
    String fileName;
    int p,g,y,k;
    FileToBit readF = new FileToBit();
    StringBuilder sb;
    Padding padd = new Padding();
    DecToBinary d2b = new DecToBinary();
    FastExponential fExpo = new FastExponential();
    String cipherA="",cipherB="";
    PrintWriter writeCipherA;
    PrintWriter writeTest;
    PrintWriter writeCipherB;
    int block;
    
    
    public Encryption() {
        GetKey getKey = new GetKey("PublicKey.txt");
        p = Integer.parseInt(getKey.getA());
        g = Integer.parseInt(getKey.getB());
        y = Integer.parseInt(getKey.getC());
    }
    
    public boolean encrypt(String file,int b){
        fileName =file;
        block=b;
        //find k
        k=getK();
        
        //read file
        sb = readF.toBit(fileName);
        String sbn = sb.toString();
        System.out.println(sbn);
        
        //padding
        if(sbn.length()%b!=0)sbn = sbn+padd.getPadding(sbn.length(),b);
        
        //encrypt
        while(sbn.length()>b){
            getCipher(Integer.parseInt(sbn.substring(0,b),2));
            sbn=sbn.substring(b);
        }
        
//        System.out.println(cipherA.length()%b);
//        System.out.println(cipherB.length()%b);
        
        //write File
        if(writeFile())System.out.println("[Write file Complete!!]");
        
        return true;
    }
    
    private int getK() {
        Random ran = new Random();
        int k;
        ExtendedEuclidGCD exU = new ExtendedEuclidGCD();
        do{
            k=ran.nextInt(p-2)+1;
        }while (!exU.getGCD(k, p-1));
        System.out.println("k : "+k);
        return k;
    }
    
    private void getCipher(int in){
        int a,b;
        long temp;
        a= fExpo.getFastExpo(g,k,p);
        b=fExpo.getFastExpo(y,k,p);
        temp=Math.floorMod((long)(b*in),p);
        b = (int) temp;
        System.out.println(a+" "+b);
        //adding
        String prefillA,prefillB;
        prefillA = d2b.convertToBi(a);
        prefillB = d2b.convertToBi(b);
        System.out.println(prefillA+" "+prefillB);
        while(prefillA.length()<block){prefillA="0"+prefillA;}
        while(prefillB.length()<block){prefillB="0"+prefillB;}
        cipherA = cipherA+prefillA;
        cipherB = cipherB+prefillB;
    }
    
    private boolean writeFile(){
        try {
//            writeCipherA = new PrintWriter("CipherA.txt");
//            writeCipherB = new PrintWriter("CipherB.txt");
//            writeCipherA.println(cipherA);
//            writeCipherB.println(cipherB);
//            writeCipherA.close();
//            writeCipherB.close();
            writeTest = new PrintWriter("text.txt");
            writeTest.println(cipherA);
            writeTest.println(cipherB);
            writeTest.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
