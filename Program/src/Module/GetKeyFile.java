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
public class GetKeyFile {
    
    
    public GetKeyFile(){}
    
    public int getKey(String fileName,int block){
        //GetFile
        ReadFiletoBit readF = new ReadFiletoBit();
        //get Binary from File;
	StringBuilder sb = readF.read(fileName);
        //System.out.println(sb);
        int key;
        int n=0;
        String bitKey = "";
        boolean until1=false;
        
        for(int i = 0 ;n<block;i++){
            String test = sb.toString().substring(i, i+1);
            if(test.equals("1")||until1==true){
                bitKey = bitKey+test;
                //System.out.print(test);
                n++;
                until1=true;
            }
        }
        //if file is't long enough?
        key = Integer.parseInt(bitKey, 2);
        return key;
    }
    
}
