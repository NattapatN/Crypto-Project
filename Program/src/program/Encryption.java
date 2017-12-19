/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Encryption {
    static String filename;
    static PrintWriter writeCipher;
    static int p,g,y;
    static int k;
    static int block;
    static String cipherA="",cipherB="";
    Padding padding = new Padding();
    static DectoBinary d2b = new DectoBinary();
    InFiletoBit readF = new InFiletoBit();
    StringBuilder sb ;
    
    public Encryption(){
        GetKey getKey = new GetKey("PublicKey.txt");
        p=Integer.parseInt(getKey.getA());
        g=Integer.parseInt(getKey.getB());
        y=Integer.parseInt(getKey.getC());
    }
    
    public void Encrypt(String file,int b){ 
        filename = file;
        FindK fk= new FindK(p-1);
        //find k gcd
        k = fk.getK();
        block=b;
        int goEnc;
        String encpy="";
        sb = readF.toBit("test.txt");
        String sbn = sb.toString();
        System.out.println("File : "+sbn);
        int i =0;
        //System.out.println("legth : "+sbn.length());
        sbn = sbn+padding.getPadding(sbn.length(), block);
        while (sbn.length()>1){
            //full block
            if(i!=0&&i%block==0){
                goEnc = Integer.parseInt(encpy, 2);
                //System.out.println("Plain text "+d2b.getBinary(goEnc,block));
                goEncrypt(goEnc);
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
            i++;
        }
        System.out.println("Cipher A : "+cipherA);
        System.out.println("Cipher B : "+cipherB);
    }
    public static void goEncrypt(int in){
        FastExponential fExpo = new FastExponential();
        BitToText b2t = new BitToText();
        int cA = fExpo.getFastExpo(g, k, p);
        long cB = fExpo.getFastExpo(y, k, p);
        cB = Math.floorMod((cB*in),p);
        String cAs = d2b.getBinary(cA,block);
        String cBs = d2b.getBinary((int)cB,block);
        cipherA = cipherA+cAs;
        cipherB = cipherB+cBs;
        try {
            writeCipher = new PrintWriter(filename);
            writeCipher.println(cipherA);
            writeCipher.println(cipherB);
            writeCipher.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
