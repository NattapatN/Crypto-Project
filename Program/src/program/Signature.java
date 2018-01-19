/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NattapatN
 */
public class Signature {
    String filename;
    Random ran = new Random();
    PrintWriter writeSign;
    FastExponential fExpo = new FastExponential();
    ExtendedEuclidGCD ex = new ExtendedEuclidGCD();
    int m,k;
    int p,g,u;
    int r;
    long s;
    public Signature(){}
    
    public boolean sign(String file){
        filename = file;
        //get U
        GetKey getSk =new GetKey("PrivateKey1.txt");
        u=Integer.parseInt(getSk.getA());
        
        //get p g
        GetKey getPk =new GetKey("PublicKey1.txt");
        p=Integer.parseInt(getPk.getA());
        g=Integer.parseInt(getPk.getB());
        
        //get msg
        GetKey getMsg =new GetKey(filename);
        m = Integer.parseInt(getMsg.getA());
        
        //find k
        do{
            k=ran.nextInt(p-2)+2;
        }while(!ex.getGCD(k, p-1));
        
        //cal r
        r=fExpo.getFastExpo(g, k, p);
        
        int invertK=ex.getA2();
        s = Math.floorMod((long)u*r, p-1);
        s=m-s;
        if(s<0)s=p-1-s;
        s=Math.floorMod((long)s*invertK, p-1);
        
        //write signature
        writeSign(); 
        
        return true;
    }
    
    private void writeSign(){
        
        try {
            writeSign = new PrintWriter("sign.txt");
            writeSign.println(r);
            writeSign.println((int)s);
            writeSign.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Signature.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
