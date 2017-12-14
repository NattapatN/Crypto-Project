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
public class Encryption {
    int p,g,y;
    ReadFiletoBit readF = new ReadFiletoBit();
    StringBuilder sb ;
    public Encryption(int ip,int ig,int iy){
        p=ip;
        g=ig;
        y=iy;
    }
    
    public void Encrypt(int b){ 
        int block=b;
        String encpy;
        sb = readF.read("test.txt");
        System.out.println(sb);
        //for (int i=0;i<sb.length();i++){}
        
    }
    
}
