/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackUp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import Module.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author NattapatN
 */
public class Encryption {
    //define
    static PrintWriter writeCipherA;
    static PrintWriter  writeCipherB;
    String fileName;
    int blockSize;
    int p,g,y;
    static int k;
    byte a,b;
    String cipherA="",cipherB="";
    Random ran = new Random();
    FastExponential fExpo = new FastExponential();
    DecToBinary d2b = new DecToBinary();
    ExtendedEuclidGCD exGCD = new ExtendedEuclidGCD();
    
    public Encryption(String fName,int bSize){
        fileName = fName;
        blockSize = bSize;
        FileToBit f2b = new FileToBit();
        Padding padding = new Padding();
        GetKey getKey = new GetKey("PublicKey.txt");        
        String bitFile;
        
        //String to bit
        bitFile = f2b.toBit(fileName).toString();
        System.out.println(bitFile);
        
        //Padding
//        System.out.println(bitFile.length());
        if(bitFile.length()%blockSize!=0){
            bitFile = bitFile+padding.getPadding(bitFile.length(), blockSize);
        }
//        System.out.println(bitFile);
        
        //get public key,
        p = Integer.parseInt(getKey.getA()) ;
        g = Integer.parseInt(getKey.getB()) ;
        y = Integer.parseInt(getKey.getC()) ;
//        System.out.println(p+" "+g+" "+y);
        //find K
        do{
        k = ran.nextInt(p-2)+1;
        }while (exGCD.getGCD(k,p-1));
        
        //Encryption
        boolean eof = false;
        String temp;
        while(!eof){
            //loop encrypt
            temp = bitFile.substring(0,blockSize);
            getEncrypt(temp);
//            System.out.println(bitFile.length());
            if(bitFile.length()<=blockSize){
                getEncrypt(bitFile);
                eof=true;
            }
            else{
                bitFile = bitFile.substring(blockSize);
            }
        }
        //convert to text
//        cipherA = b2t.getText(cipherA);
//        cipherB = b2t.getText(cipherB);
//        System.out.println(cipherA.length());
//        System.out.println(cipherB.length());
        
        
        //Write Cipher File,
        System.out.println(cipherA);
        System.out.println(cipherB);
        if(writeFile())System.out.println("[Cipher File write complete!!]");
//        String aa=cipherA;
//        String bb = cipherB;
//        boolean a ;
//        while(aa.length()>8){
//            newWriteFile(Integer.parseInt(aa.substring(0,8),2),Integer.parseInt(bb.substring(0,8),2));
//            aa= aa.substring(8);
//            bb=aa.substring(8);
//        }
    }
    
    private void getEncrypt(String inn){
        int in = Integer.parseInt(inn,2);
//        System.out.println(in);
        String a,b;
        int temp;
        a=d2b.convertToBi(fExpo.getFastExpo(g, k, p));
        temp=Math.floorMod(in*(fExpo.getFastExpo(y, k,p)),p);
        b = d2b.convertToBi(temp);
        
        //fill full block
//        System.out.println(a.length());
//        System.out.println(b.length());
        while(a.length() < blockSize) {
            a = "0" + a;
        }
        while(b.length() < blockSize) {
            b = "0" + b;
        }

        cipherA = cipherA + a;
        cipherB = cipherB+b; 
//        System.out.println(cipherA+" "+cipherB);
    }
    
    private boolean writeFile() {
        try {
            writeCipherA = new PrintWriter ("CipherA.txt");
            writeCipherB = new PrintWriter ("CipherB.txt");
            
            //write cipher to file
            writeCipherA.print(cipherA);
            writeCipherB.print(cipherB);
//            String ca, cb;
//
//            try {
//                do {
//                    ca = cipherA.substring(0, 8);
//                    cb = cipherB.substring(0, 8);
//                    if(ca.length()==8&&ca.substring(0,1).equals("1"))ca="-"+ca.substring(1);
//                    if(cb.length()==8&&cb.substring(0,1).equals("1"))cb="-"+cb.substring(1);
//                    writeCipherA.write(Byte.parseByte(ca,2));
//                    writeCipherB.write(Byte.parseByte(cb,2));
//                    if (cipherA.length() >= 8) {
//                        cipherA = cipherA.substring(8);
//                        cipherB = cipherB.substring(8);
//                    }
//                } while (cipherA.length() >= 8);
//
                writeCipherA.close();
                writeCipherB.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
//            }


        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    private boolean newWriteFile(int a,int b){
        String aw = null;
        String bw = null;

        aw = String.format("%8s", Integer.toBinaryString(a)).replace(' ', '0');    // 8 Or 16 bit
        bw = String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
        
        File fileA = new File("ciherA.txt");
        if (!fileA.exists()) {
            try {
                fileA.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File fileB = new File("ciherA.txt");
        if (!fileB.exists()) {
            try {
                fileB.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        try {
            BufferedWriter bufA = new BufferedWriter(new FileWriter(fileA, true));
            BufferedWriter bufB = new BufferedWriter(new FileWriter(fileB, true));
            bufA.append(aw);
            bufB.append(bw);
            bufA.close();
            bufB.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        return true;
    }
}
