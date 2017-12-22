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
import static program.Encryption.writeCipher;

/**
 *
 * @author ธนากร
 */
public class Hash {
    static int p;
    public int boxbit;
    public int box=7;
    public String bbox[]=new String[8];
    public long nbox[]=new long[8];
    public String boxstring;
    ReadFiletoBit readF = new ReadFiletoBit();
    StringBuilder sb ;
    public int numstring;
    PrintWriter writeHash;
    static int sumhash;
    public int numbox;
    GetKey getKey = new GetKey("PrivateKey.txt");
    public Hash(){}
    
    
    
    public  void SumHash(String filename,int bBit){
        sumhash=0;
        p=Integer.parseInt(getKey.getB());
        boxbit=bBit;
        sb = readF.read(filename);
        boxstring = sb.toString();
        padd();
        numbox=1+numbox+(numstring/boxbit);
        //System.out.println("จำนวนกล่อง"+numbox);
        int h[]=new int [numbox];
        h[0]=numstring;
        int i;
        //System.out.println("H0 is "+h[0]);
        for(i=1;i<=numbox-1;i++){
            //System.out.print("H"+i+" is ");
            Subbox();
            bittonum(p);
            for(int j=0;j<=box;j++){
                h[i]= (int) ((h[i]+nbox[j])%p);
            }
           h[i]=(h[i-1]+h[i]);
           //System.out.println(h[i]);
        }
        //System.out.println(boxstring);
        for(int j=0;j<=numbox-1;j++){
            sumhash=(sumhash+h[j])%p;
        }
        System.out.println("Hash Funtion Is "+sumhash+" (Mod "+p+").");
        
        if(writeHash()){System.out.println("Hash complete!!");}
}


    
    public void padd(){
        numstring=boxstring.length();
        int num;
        num=numstring%boxbit;
        if(num!=0){
            numbox=numbox+1;
        }
        //System.out.println("จำนวนสตริง"+numstring);
        //System.out.println("เศษเหลือ"+num);
        
        boolean lastbit=true;
        if(num!=0){
            System.out.println("StringBeforePadding  is  "+boxstring);
            System.out.println("NumberOfPadding   "+(boxbit-num));
             if(lastbit=boxstring.endsWith("1")) {
                 for(int i =1;i<=boxbit-num;i++){
                     boxstring=boxstring.concat("1");
                     }
                    }
             else {
                  for(int i =1;i<=boxbit-num;i++){
                    boxstring=boxstring.concat("0"); 
                   }
                  }
             System.out.println("StringAfterPadding  is   "+boxstring);
        }
        else {System.out.println();
              System.out.println("Not Padding.");
             }
        System.out.println();
        System.out.println();
    //numstring=boxstring.length();
    //num=numstring%boxbit;
    //System.out.println(num);
     
    

    
        }
     
    public void Subbox(){
        //System.out.println(boxstring);
        for(int i =0;i<box;i++){
            bbox[i]=boxstring.substring(0,8);
          // System.out.println(bbox[i]);
            boxstring=boxstring.substring(8);
            i++;
        }
       // System.out.println(boxstring);
    }
    public void bittonum(int p){
        for(int i=0;i<box;i++){
            //System.out.println("i is "+i);
                    nbox[i]=Integer.parseInt(bbox[i],2);
                    nbox[i]=nbox[i]%p;
                  // System.out.println("box"+i+"is"+nbox[i]);
            }
        }
    public int gethash(){return sumhash;}
    
    public boolean writeHash() {
        try {
            writeHash = new PrintWriter("Hash.txt");
            writeHash.println(sumhash);
            writeHash.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    }

