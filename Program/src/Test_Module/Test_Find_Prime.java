/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Module;

import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author NattapatN
 */
public class Test_Find_Prime {
    
    public static void main(String [] args){
        //define
        Scanner scan = new Scanner(System.in);
        TestFastExpo fx = new TestFastExpo();
        Random ran = new Random();
        int p,r,temp;
        System.out.print("Enter prime : ");
        //get p & check odd
        
        p= scan.nextInt();
        if(p%2==0)p++;
        
        //test & find prime
        for (int i = 0; i < 100; i++) {
            do {
                r = ran.nextInt(p) + 1;
            } while (r == p||r<=0);

            temp = fx.getFExpo(r, (p - 1) / 2, p);
            if (temp != 1 && temp != p - 1) {
                p += 2;
                System.out.println("p : "+p);
                i = 0;
            }
        }
        
        System.out.println("Prime is "+p);

        
     
    }
}
