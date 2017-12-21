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
public class Padding {
    public Padding(){}
    
    public String fillPad(String inn,int b){
        String  in=inn+"1";
        while(in.length()<b)in=in+"0";
        return in;
    }
    public String unPad(String in) {
        return null;
    }
    
    
}
