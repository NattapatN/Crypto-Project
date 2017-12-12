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
public class GetKeyFromFile {
    int key;
    String bitKey="";
    public GetKeyFromFile(){}
    
    public int getKey(){
        //GetFile
        ReadFiletoBit readF = new ReadFiletoBit();
        //get Binary from File;
	StringBuilder sb = readF.read("test.txt");
        
        boolean until1=false;
        for(int i = 0 ;i<24;){
            String test = sb.toString().substring(i, i+1);
            if(test.equals("1")||until1==true){
                bitKey = bitKey+test;
                i++;
                until1=true;
            }
        }
        //if file is't long enough?
        key = Integer.parseInt(bitKey, 2);
        return key;
    }
    
}
