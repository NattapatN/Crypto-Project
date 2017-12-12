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
public class TestReadFile {
    
    public static void main(String [] args){
        int key;
        String bitKey="";
	ReadFiletoBit readF = new ReadFiletoBit();
	StringBuilder sb = readF.read("test.txt");
		//writeBinaryFile(sb);
        System.out.println(sb);
        boolean until1=false;
        for(int i = 0 ;i<24;i++){
            String test = sb.toString().substring(i, i+1);
            if(test.equals("1")||until1==true){
                System.out.print(test);
                bitKey = bitKey+test;
                until1=true;
            }
        }
        key = Integer.parseInt(bitKey, 2);
        System.out.println();
        System.out.println(key);
    }
    
}
