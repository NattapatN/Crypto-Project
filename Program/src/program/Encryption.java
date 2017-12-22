/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Encryption {
    static PrintWriter writeCipher;
    PrintWriter writetest;
    static int p,g,y;
    static int k;
    static int block;
    static String cipherA="",cipherB="";
    Padding padding = new Padding();
    static DectoBinary d2b = new DectoBinary();
    ReadFiletoBit readF = new ReadFiletoBit();
    StringBuilder sb ;
    static long padd;
    
    public Encryption(){
        GetKey getKey = new GetKey("PublicKey.txt");
        p=Integer.parseInt(getKey.getA());
        g=Integer.parseInt(getKey.getB());
        y=Integer.parseInt(getKey.getC());
    }
    
    public void Encrypt(String file,int b,String fileout){ 
        
        FindK fk= new FindK(p-1);
        //find k gcd
        k = fk.getK();
        block=b;
        int goEnc;
        String encpy="";
        sb = readF.read(file);
        String sbn = sb.toString();
        System.out.println("File : "+sbn);
        int i =0;
        //System.out.println("legth : "+sbn.length());
        System.out.println(sbn.length());
        padd=sbn.length()% block;
        padd=(block-padd)%block;
        System.out.println(padd);
        sbn = sbn+padding.getPadding(sbn.length(), block);
        boolean end = false;
        while (!end){
            //full block
            if(i!=0&&i%block==0){
                goEnc = Integer.parseInt(encpy, 2);
                //System.out.println("Plain text "+d2b.getBinary(goEnc,block));
                goEncrypt(goEnc,fileout);
                //key = Integer.parseInt(bitKey, 2);
                i=0;
                goEnc=0;
                encpy="";
            }
            if (sbn.length()>1) {
                //add string
                encpy = encpy + sbn.substring(0, 1);
                //substring
                sbn = sbn.substring(1);
            }
            else{
                encpy= encpy +sbn;
                end=true;
            }
            i++;
        }
        goEnc = Integer.parseInt(encpy, 2);
        //System.out.println("Plain text "+d2b.getBinary(goEnc,block));
        goEncrypt(goEnc, fileout);
        
        try {
            writeCipher = new PrintWriter(fileout);
            writeCipher.println(cipherA);
            writeCipher.println(cipherB);
            writeCipher.println(padd);
            writeCipher.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        aa(fileout);
        System.out.println(cipherA.length());
        System.out.println(cipherB.length());
        System.out.println("Cipher A : "+cipherA);
        System.out.println("Cipher B : "+cipherB);
    }
    public static void goEncrypt(int in,String fileout){
        FastExponential fExpo = new FastExponential();
        int cA = fExpo.getFastExpo(g, k, p);
        long cB = fExpo.getFastExpo(y, k, p);
        cB = Math.floorMod((cB*in),p);
        String cAs = d2b.getBinary(cA,block);
        String cBs = d2b.getBinary((int)cB,block);
        cipherA = cipherA+cAs;
        cipherB = cipherB+cBs;
        
    }
    
    private void aa(String fileout){
        try {
            writeCipher = new PrintWriter("1.txt");
            writeCipher.println(sb);
            writeCipher.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
