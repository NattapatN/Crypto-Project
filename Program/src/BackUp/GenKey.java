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
import program.Encryption;
/**
 *
 * @author NattapatN
 */
public class GenKey {
    String fileName;
    int keySize;
    int p,g,y,u;
    static PrintWriter writePublicKey;
    static PrintWriter writePrivateKey;
    Module.FastExponential fExpo = new Module.FastExponential();
    Module.LehmahTest lTest = new Module.LehmahTest();
    
    public GenKey(String fname,int kSize){
        //define
        fileName = fname;
        keySize = kSize;
        
        //get file 
        int key = readFile();
        
        //check odd 
        if(key%2==0)key++;
        
        //find (safe) prime, p
        p = getKey(key);
        
        //find generator (g)
        Random ran = new Random();
        int temp;
        g = ran.nextInt(p-1)+1;
        temp = fExpo.getFastExpo(g,(p-1)/2,p);
        //check g
        if(temp==1)g=p-g;
        
        //gen u
        u = ran.nextInt(p-1)+1;
        
        //calculate y
        y = fExpo.getFastExpo(g,u,p);
        
        //print out
        System.out.println("----------------------<KEY>----------------------");
        System.out.println("Public Key (p,g,y) : ("+p+", "+g+", "+y+")");
        System.out.println("Private Key (u) : "+u);
        
        //write to file 
        if(writeFile()){
            System.out.println("[Write File comlete!!!]");
        }
    }
    
    //output : keyfile
    private int readFile(){
        //GetFile
        Module.ReadFiletoBit readF = new Module.ReadFiletoBit();
        //get Binary from File;
	StringBuilder sb = readF.read(fileName);
//        System.out.println(sb);
        int key = 0;
        int n=0;
        String bitKey = "";
        boolean until1=false;
        int i;
        for(i = 0 ;n<keySize;i++){
            String test = sb.toString().substring(i, i+1);
            if(test.equals("1")||until1==true){
                bitKey = bitKey+test;
                //System.out.print(test);
                n++;
                until1=true;
            }
        }
        //if file is't long enough?
        if (i < keySize) {
            Module.Padding padding = new Module.Padding();
            bitKey = bitKey+padding.getPadding(sb.length(), keySize);
        }
        
        key = Integer.parseInt(bitKey, 2);
        return key;
    }
    
    //output : real key
    private int getKey(int key){
        Boolean foundKey = false;
        
        while(!foundKey){
            foundKey = lTest.isPrime(key);
            if(foundKey){
                if(!lTest.isPrime((key-1)/2))key+=2;
            }
            else{key+=2;}
        }
        return key;
    }
    
    //write key to file
    private boolean writeFile(){
        try {
            writePublicKey = new PrintWriter("PublicKey.txt");
            writePrivateKey = new PrintWriter("PrivateKey.txt");
            writePublicKey.println(p);
            writePublicKey.println(g);
            writePublicKey.println(y);
            writePrivateKey.println(p);
            writePrivateKey.println(u);
            
            writePublicKey.close();
            writePrivateKey.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}
