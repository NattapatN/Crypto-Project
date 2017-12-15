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
    int g,p,u,y;
    public Genkey(){ 
        
    }
    
    public void genK(){
        //define
        Random ran = new Random();
        FastExponential fExpo = new FastExponential();
        
        
        //get p from file
        GetKeyFromFile gkff =  new GetKeyFromFile();
        p = gkff.getKey();
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
    }
    public int getP(){return p;}
    public int getG(){return g;}
    public int getY(){return y;}
    public int getU(){return u;}
    
}
