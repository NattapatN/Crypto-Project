/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

/**
 *
 * @author Nattapat
 */
public class DectoBi {
    public DectoBi(){}
    
    public String getBinary(int n,int b){
        int i=n;
        String binary="";
        while(i>0){
            binary = (i%2)+binary;
            i=i/2;
        }
        while(binary.length()<b){binary ="0"+ binary;}
        return binary;
    }
    
}
