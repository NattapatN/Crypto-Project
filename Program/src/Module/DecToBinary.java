/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

/**
 *
 * @author NattapatN
 */
public class DecToBinary {
    public DecToBinary(){}
    
    public String convertToBi(int inn){
        int in = inn;
        String binary="";
        while(in>0){
            binary = (in%2)+binary;
            in/=2;
        }
        return binary;
    }
}
