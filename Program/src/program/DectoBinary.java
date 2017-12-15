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
public class DectoBinary {
    public DectoBinary(){}
    
    public String getBinary(int in,int b){
        int n=in;
        String binary="";
        while(n!=0){
            if(n%2==0){
                binary=0+binary;
            }
            else{
                binary=1+binary;
            }
            n=n/2;
        }
        while(binary.length()<b){
            binary=0+binary;
        }
        return binary;
    }
    
}
