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
public class Padding {
    public Padding(){}
    
    public String getPadding(int length,int block){
        String padding="1";
        int forPad = block-(length%block)-1;
        //System.out.println("padding : " +forPad);
        for(int i=1;i<=forPad;i++){
            padding = padding +"0";
        }
        //System.out.println("padding : " +padding);
        return padding;
    }
    public String UnPad(String filepad){
        boolean finish= false;
        int len=filepad.length();
        while(!finish){
            if(filepad.substring(len-1,len).equals("1")){
                finish=true;
            }
            len--;
        }
        return filepad.substring(0,len);
    }
    
}
