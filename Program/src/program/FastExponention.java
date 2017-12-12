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
public class FastExponention {
    
    int power,testNum;
    
    public FastExponention(){}
    
    public int getFastExpo(int num){
        testNum=num;
        power = (num-1)/2;
        Random ran = new Random();
        int rand;
        for(int i=0;i<100;i++){
            rand = ran.nextInt(testNum);
            rand = getNumNTest(rand);
            if(rand!=1||rand!=testNum-1){
                testNum+=2;
                i=0;
            }
        }

        return testNum;
    }
    
    private int getNumNTest(int num){
        int power = this.power;
        int base = num;
        int result = 1;
        String bitPow = Integer.toBinaryString(power);
        int lenpower = bitPow.length();
        
        int [] fastExpo = new int[lenpower];
        fastExpo[0] = base;
        for(int i=1;i<lenpower;i++){
            fastExpo[i] = (fastExpo[i-1]*fastExpo[i-1])%testNum;
        }
        for(int j=lenpower-1;j>=0;j--){
            int powerin=(int) Math.pow(2, j);
            if(powerin<=power&&power!=0){
                result =(result*fastExpo[j])%testNum; 
                power-=powerin;
            }
        }
        return result;
    }
    
}
