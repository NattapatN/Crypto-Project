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
        //Define
        Scanner scan = new Scanner(System.in);
        int num;
        boolean notFound = true;
        
        //Get input Number
        System.out.print("Enter Number : ");
        num = scan.nextInt();
        
        //Check odd
        if(num%2==0) num++;
        
        //Fine Prime
        while(notFound){
            notFound = isPrime(num);
            if(notFound == true){
                num+=2;
                System.out.println(num);
            }
        }
        System.out.println(num);
        
    }
    
    private static boolean isPrime(int inn){
        //Define
        Random ran = new Random();
        int in = inn;
        int test;
        
        //Check prime by fast expo
        for(int i = 0;i<100;i++){
            //random tester
            do{
            test = ran.nextInt(in+1);
            }while(test==0||test==in);
            System.out.print(test+" = ");
            
            //test tester
            test = fastExpo(test,(in-1)/2);
            System.out.println(test);
            
            //check tester
            if(test!=1&&test!=in-1) return true;
        }
        return false;
    }
    
    private static int fastExpo(int tester,int power){
        //Define
        int test = tester;
        String bitPow = Integer.toBinaryString(power);
        int lenBitPow = bitPow.length();
        int result = 1;
        int [] fexpo = new int[lenBitPow];
        
        //cal power
        fexpo[0]=test;
        for(int i=1;i<lenBitPow;i++){
            fexpo[i]=Math.floorMod((fexpo[i-1]*fexpo[i-1]),((power*2)+1));
            
        }
        for(int j =lenBitPow-1;j>=0;j--){
            if(bitPow.substring(j,j+1).equals("1")){
                result=Math.floorMod(result*(fexpo[j]),((power*2)+1));
            }
        }
        return result;
    }
}
