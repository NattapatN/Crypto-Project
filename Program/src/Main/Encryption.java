/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Random;

/**
 *
 * @author NattapatN
 */
public class Encryption {
    //define
    String fileName;
    int blockSize;
    String p,g,y;
    static int k;
    String cipherA="",cipherB="";
    Random ran = new Random();
    Module.FastExponential fExpo = new Module.FastExponential();
    Module.ExtendedEuclidGCD exGCD = new Module.ExtendedEuclidGCD();
    
    public Encryption(String fName,int bSize){
        fileName = fName;
        blockSize = bSize;
        Module.FileToBit f2b = new Module.FileToBit();
        Module.Padding padding = new Module.Padding();
        Module.GetKey getKey = new Module.GetKey("Encryption.txt");
        String bitFile;
        
        //String to bit
        bitFile = f2b.toBit(fileName).toString();
        
        //Padding
        if(bitFile.length()%blockSize==0){
            bitFile = bitFile+padding.getPadding(bitFile.length(), blockSize);
        }
        
        //get public key,
        p = getKey.getA();
        g = getKey.getB();
        y = getKey.getC();
        
        //find K
        do{
        k = ran.nextInt(Integer.parseInt(p)-2)+1;
        }while (exGCD.getGCD(k,Integer.parseInt(p)-1));
        
        //Encryption
        boolean eof = false;
        String temp;
        while(!eof){
            //loop encrypt
            temp = bitFile.substring(0,blockSize);
            getEncrypt(temp);
            if(bitFile.length()<=blockSize)bitFile.substring(blockSize);
        }
        
        //Write Cipher File,
    }
    
    private void getEncrypt(String inn){
        int in = Integer.parseInt(inn);
        cipherA =cipherA+ fExpo.getFastExpo(g,k , in);
        
    }
}
