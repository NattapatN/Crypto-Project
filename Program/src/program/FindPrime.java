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
public class FindPrime {
    public FindPrime(){}
    
    public int getPrime(int num){
        int prime =num;
        boolean found = false;
        ElgamalTest eTest = new ElgamalTest();
        
        //Check odd
        if(prime%2==0) prime++;
        
        //Fine Prime
        while(!found){
            if(eTest.isPrime(prime)){
                if (eTest.isPrime((prime - 1) / 2)) {
                    found=true;
                }
                else{
                    prime += 2;
                }
            }
            else{
                prime += 2;
            }
        }
        return prime;
    }
    
}
