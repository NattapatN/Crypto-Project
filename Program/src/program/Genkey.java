/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.util.Random;
/**
 *
 * @author NattapatN
 */
public class Genkey {
    public Genkey(){ 
        
    }
    
    public void genK(){
        //define
        int g,p,u,y;
        Random ran = new Random();
        FastExponential fExpo = new FastExponential();
        
        
        //get p from file
        GetKeyFromFile gkff =  new GetKeyFromFile();
        p = gkff.getKey();
        //System.out.println("-------------------------------------------------");
        //System.out.println("p = "+p);
        
        //find Real p!!(Safe )
        FPrime fPrime = new FPrime();
        p =fPrime.getPrime(p);
        //System.out.println("Safe prime = "+p);
        
        //find g
        do {
            g = ran.nextInt(p);
        } while (g == 0 || g == p);
        g = fExpo.getFastExpo(g,(p-1)/2,p);
        //check g
        if(g==1)g=p-g;
        
        //Random u
        do {
            u = ran.nextInt(p);
        } while (u == 0 || u == p);
        
        //create y
        y=fExpo.getFastExpo(g, u, p);
        System.out.println("--------------------------------------------------");
        System.out.println("Public key is : ("+p+", "+g+", "+y+")");
        System.out.println("Private key is : "+u);
    }
    
}
