/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import static program.Hash.sumhash;

/**
 *
 * @author NattapatN
 */
public class Hash1 {
    int k,p;
    FastExponential fExpo = new FastExponential();
    int [] box;
    int block =24;
    PrintWriter writeHash;
    ReadFiletoBit readF = new ReadFiletoBit();
    String sb;
    public Hash1(int kin){
        k=kin;
        box = new int [k];
    }
    
    public boolean hashing (String filename){
        sb = readF.read(filename).toString();
        GetKey getKey = new GetKey("PublicKey.txt");
        p=Integer.parseInt(getKey.getA());
        
        //padding
        String temp = sb.substring(sb.length()-1,sb.length());
        while(sb.length()%block!=0){sb=sb+temp;}
        
        //hash
        boolean eob =false;
        box[0]=sb.length();
        while(!eob){
            box[0]=fExpo.getFastExpo(box[0], 2, p);
            for(int i=1;i<k;i++){
                if(sb.length()>0){
                    box[i]=Integer.parseInt(sb.substring(0, block),2);
                    sb= sb.substring(block);
                }
                else{
                    box[i]=0;
                    eob=true;
                }
                box[0]+=box[i];
            }
            
        }
        
        System.out.println(box[0]);
        
        //write hash
        writeHash();
        
        return true;
    }
    
    public void  writeHash() {
        try {
            writeHash = new PrintWriter("hash.txt");
            writeHash.println(box[0]);
            writeHash.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hash1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getHash(){
        return box[0];
    }
    
}
