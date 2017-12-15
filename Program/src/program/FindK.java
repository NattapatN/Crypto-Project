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
        int k=ran.nextInt(p-2)+1;
        ExtendedEuclidGCD exU = new ExtendedEuclidGCD();
        while (!exU.getGCD(k, p-1)){
            k=ran.nextInt(p-2)+1;
        }
        System.out.println("k : "+k);
        return k;
    }
}
