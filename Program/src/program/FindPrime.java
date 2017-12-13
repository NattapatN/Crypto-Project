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
        boolean notFound = true;
        
        //Check odd
        if(prime%2==0) prime++;
        
        //Fine Prime
        while(notFound){
            notFound = isPrime(prime);
            if(notFound == true){
                prime+=2;
            }
        }
        
        return prime;
    }
    
    private static boolean isPrime(int inn){
        //Define
        FastExponential fExpo = new FastExponential();
        Random ran = new Random();
        int in = inn;
        int test;
        
        //Check prime by fast expo
        for(int i = 0;i<100;i++){
            //random tester
            do{
                test = ran.nextInt(in)+1;
            }while(test==in);
            //System.out.print(test+" = ");
            
            //test tester
            test = fExpo.getFastExpo(test,(in-1)/2,in);
            //System.out.println(test);
            
            //check tester
            if(test!=1&&test!=in-1) return true;
        }
        return false;
    }
}
