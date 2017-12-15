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
public class FindK {
    int p;
    public FindK(int pin){
        p= pin;
    }
    
    public int getK(){
        Random ran = new Random();
        int k=ran.nextInt(p-1)+1;
        ExtendedEuclidGCD exU = new ExtendedEuclidGCD();
        int gcd=exU.getGCD(k,p);
        while (gcd!=1){
            k=ran.nextInt(p-1)+1;
            gcd=exU.getGCD(k,p);
        }
        System.out.println("k : "+k);
        return k;
    }
}
