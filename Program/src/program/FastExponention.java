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
        boolean notPrime= true;
        while(notPrime){
            
        }
        
        
        
        return 0;
    }
    
    private int getNumNTest(int num){
        int numb=num;
        int nowpower = power;
        int [] powerCompo = new int[24];
        int n=1,i=0;
        int result=1;
        while(nowpower!=0){
            if(n>=nowpower){
                n=n/2;
                powerCompo[i]=n;
                nowpower -= n;
                n=1;
                i++;
            }
            n*=2;
            
        }
        
        for(int t=1;i<=0;t++){
            if(t==powerCompo[i]){
                result = (result*numb)%testNum;
            }
            else{
                numb=(int) (Math.pow(numb,2)%testNum);
            }
        }
        return result;
    }
    
}
