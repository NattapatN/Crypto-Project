/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Module;

import java.util.Scanner;

/**
 *
 * @author NattapatN
 */
public class Test_find_Prime2 {
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter : ");
        int i =scan.nextInt();
        boolean isPrime=false;
        
        if (i % 2 == 0) i++;
        
        
        while (!isPrime){
            isPrime=sPrime(i);
            if(isPrime){
                isPrime=sPrime((i-1)/2);
                if (!isPrime) {
                    i+=2;
                }
            }
            else{
                i+=2;
            }
        }
        
        System.out.println(i);
        
    }
    private static boolean sPrime(int x){
        for (int y = 2; y <= x/2; y++) {
            if (x % y == 0) {
                return false;
            }
        }
        return true;
    }
}
