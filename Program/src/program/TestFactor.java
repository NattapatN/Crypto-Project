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
public class TestFactor {
    static int num;
    static int power;
    public static void main(String []args){
        num=100;
        power = (num-1)/2;
        Random ran = new Random();
        int rand;
        for(int i=0;i<100;i++){
            rand = ran.nextInt(num+1);
            System.out.print(rand+" >> ");
            rand = getNumNTest(rand);
            System.out.println(rand);
            if(rand!=1&&rand!=num-1){
                num+=2;
                System.out.println(num);
                i=0;
            }
        }  
    }
    private static int getNumNTest(int numn){
        int powerr = power;
        int base = numn;
        int result = 1;
        String bitPow = Integer.toBinaryString(powerr);
        int lenpower = bitPow.length();
        
        int [] fastExpo = new int[lenpower];
        fastExpo[0] = base;
        for(int i=1;i<lenpower;i++){
            fastExpo[i] = (fastExpo[i-1]*fastExpo[i-1])%num;
        }
        for(int j=lenpower-1;j>=0;j--){
            int powerin=(int) Math.pow(2, j);
            if(powerin<=powerr&&powerr!=0){
                result =(result*fastExpo[j])%num; 
                powerr-=powerin;
            }
        }
        return result;
    }
}
