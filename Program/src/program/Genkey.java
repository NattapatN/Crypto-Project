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
public class Genkey {
    PrintWriter writePublic ;
    PrintWriter writePrivate ;
    int g,p,u,y;
    public Genkey(){ 
        
    }
    
    public void genK(String filename,int size){
        Random ran = new Random();
        FastExponential fExpo = new FastExponential();
        
        //get p from file
        GetKeyFromFile gkff =  new GetKeyFromFile();
        p = gkff.getKey(filename,size);
        //System.out.println("-------------------------------------------------");
        //System.out.println("p = "+p);
        
        //find Real p!!(Safe )
        FindPrime fPrime= new FindPrime();
        //FPrime fPrime = new FPrime();
        p =fPrime.getPrime(p);
        //System.out.println("Safe prime = "+p);
        
        //find g
        int temp;
        g = ran.nextInt(p-1)+1;
        temp = fExpo.getFastExpo(g,(p-1)/2,p);
        //check g
        if(temp==1)g=p-g;
        
        //Random u
        u = ran.nextInt(p-1)+1;
        
        //create y
        y=fExpo.getFastExpo(g, u, p);
        System.out.println("Public key is : ("+p+", "+g+", "+y+")");
        System.out.println("Private key is : "+u);
        try {
            //define
            writePublic = new PrintWriter("PublicKey.txt");
            writePublic.println(p);
            writePublic.println(g);
            writePublic.println(y);
            writePublic.close();
            writePrivate = new PrintWriter("PrivateKey.txt");
            writePrivate.println(u);
            writePrivate.println(p);
            writePrivate.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Genkey.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getP(){return p;}
    public int getG(){return g;}
    public int getY(){return y;}
    public int getU(){return u;}
    
}
