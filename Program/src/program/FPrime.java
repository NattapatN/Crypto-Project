/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

/**
 *
 * @author NattapatN
 */
public class FPrime {
    public FPrime(){}
    
    public int getPrime(int inn){
        
        int in = inn; 
        boolean isSafePrime=false;
        //System.out.println(in);
        if (in % 2 == 0) in++;
        //System.out.println("odd P = "+in);
        while(!isSafePrime){
            isSafePrime=isPrime(in);
            if(isSafePrime){
                isSafePrime=isPrime((in-1)/2);
                if (!isSafePrime) {
                    in+=2;
                    //System.out.println("new ib is : "+ in);
                }
            }
            else{
                in+=2;
            }
        }
        
        
        return in;
    }
    private static boolean isPrime(int x){
        for (int y = 2; y <= x/2; y++) {
            if (x % y == 0) {
                return false;
            }
        }
        return true;
    }
    
}
