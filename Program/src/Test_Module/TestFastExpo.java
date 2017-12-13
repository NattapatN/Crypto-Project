/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Module;

/**
 *
 * @author NattapatN
 */
public class TestFastExpo {
    public TestFastExpo(){}
    
    public int getFExpo(int base,int power,int p){
        long ibase =base;
        long ipower = power;
        long result=1;
        
        while(ipower!=0){
            if(ipower %2==1){
                result=Math.floorMod((result*ibase),p);
                //System.out.println("result is "+result);
            }
            ipower>>=1;
            ibase = Math.floorMod((ibase*ibase),p);
            //System.out.println("base is "+ibase);
        }        
        System.out.println("base : "+ base+" Result : "+result);
        return (int)result;
    }
}
