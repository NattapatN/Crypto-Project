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
public class ExtendedEuclidGCD {
    int a2,b2;
    public ExtendedEuclidGCD(){
    }
    
    public boolean getGCD(int a,int b){
        long an=a,bn=b,a1n=1,a2n=0,b1n=0,b2n=1,r=1,q;
        long ad,bd;
        while(r!=0){
            r=Math.floorMod(an,bn);
            if (r != 0) {
                q = an / bn;
                an = bn;
                bn = r;
                ad=a1n;
                bd=b1n;
                a1n = a2n;
                b1n = b2n;
                a2n = ad - (q * a2n);
                b2n = bd - (q * b2n);
            }            
        }
        
        b2=(int)b2n;
        a2=(int)a2n;
        //System.out.println("n1 = "+a+" n2 = "+b);
        //System.out.println("GCD : "+bn);
        if(bn==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getA2(){return a2;}
    public int getB2(){return b2;}
    
}
