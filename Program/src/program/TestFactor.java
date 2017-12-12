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
public class TestFactor {
    public static void main(String []args){
        int nowpower = 456789;
        int [] powerCompo = new int[20];
        int result =1,numb=5;
        int testNum =1823;
        int n=1,i=0;
        while(nowpower==0){
            if(n>=nowpower/2){
                powerCompo[i]=n;
                nowpower -= n;
                n=1;
                i++;
            }
            n*=2;
        }
        /*for(int t=1;i>=0;t=t*2){
            if(t==powerCompo[i]){
                result = (result*numb)%testNum;
            }
            else{
                numb=(int) (Math.pow(numb,2)%testNum);
            }
        }*/
        for(int t =0;t<20;t++){
            System.out.println(powerCompo[t]);
        }
        System.out.println(result);
    }
}
