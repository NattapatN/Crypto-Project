/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Module.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Decryption {

    //define
    static FileOutputStream writePlaintext;
    int p, u;
    String message="";
    FileToBit f2b = new FileToBit();
    FastExponential fExpo = new FastExponential();
    ExtendedEuclidGCD euGCD = new ExtendedEuclidGCD();
    Padding padd = new Padding();
    DecToBinary d2b = new DecToBinary();
    String cipherA, cipherB;
    int blockSize;

    public Decryption(int bSize) {
        blockSize = bSize;
        GetKey getKey = new GetKey("PrivateKey.txt");
        cipherA = f2b.toBit("CipherA.txt").toString();
        cipherB = f2b.toBit("CipherB.txt").toString();
        System.out.println(cipherA);
        System.out.println(cipherB);
//        System.out.println(cipherA.length());
//        System.out.println(cipherB.length());

        //get key
        p = Integer.parseInt(getKey.getA());
        u = Integer.parseInt(getKey.getB());

        //loop decrypt
        boolean eof = false;
        String tempA, tempB;
        while (!eof) {
            //loop encrypt
            tempA = cipherA.substring(0, blockSize);
            tempB = cipherB.substring(0, blockSize);
            
            getDecrypt(tempA, tempB);
//            System.out.println(bitFile.length());
            if (cipherA.length() <= blockSize) {
                getDecrypt(cipherA, cipherB);
                eof = true;
            } else {
                cipherA = cipherA.substring(blockSize);
                cipherB = cipherB.substring(blockSize);
            }
        }
        //unpad
        message = padd.UnPad(message);
        
        System.out.println(message);
        
        //write into File
        if(writeFile())System.out.println("[Decryption Complete!!]");
        
    }

    private void getDecrypt(String sa, String sb) {
        //b/a^u mod p
        long cal;
        int a = Integer.parseInt(sa, 2);
        int b = Integer.parseInt(sb, 2);
        String prefill;
        a = fExpo.getFastExpo(a, u, p);
        euGCD.getGCD(p,a);
        a = euGCD.getB2();
        cal=Math.floorMod(b*a,p);
        prefill=d2b.convertToBi((int) cal);
        while(prefill.length()<blockSize){prefill = "0"+prefill;}
        
        message = message +prefill;
    }
    
    private boolean writeFile(){
        try {
            writePlaintext = new FileOutputStream("Plaintext.txt");
            String c;

            try {
                do {
                    c = message.substring(0, 8);
                    if(c.length()==8&&c.substring(0,1).equals("1"))c="-"+c.substring(1);
                    writePlaintext.write(Byte.parseByte(c,2));
                    if (message.length() >= 8) {
                        message = message.substring(8);
                    }
                    if(c.length()<8)writePlaintext.write(Byte.parseByte(message,2));
                } while (message.length() >= 8);

                writePlaintext.close();
            } catch (IOException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }


        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
