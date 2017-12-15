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
public class ExtendedEuclidGCD {
    int a2,b2;
    public ExtendedEuclidGCD(){
    }
    
    public int getGCD(int a,int b){
        long an=a,bn=b,a1n=1,a2n=0,b1n=0,b2n=1,r=1,q;
        while(r!=0){
            r=Math.floorMod(an,bn);
            if (r != 0) {
                q = an / bn;
                an = bn;
                bn = r;
                a1n = a2n;
                b1n = b2n;
                a2n = a1n - (q * a2n);
                b2n = b1n - (q * b2n);
            }            
        }
        b2=(int)b2n;
        a2=(int)a2n;
        System.out.println("n1 = "+a+" n2 = "+b);
        System.out.println("GCD : "+bn);
        return (int) bn;
    }
    
    public int getA2(){return a2;}
    public int getB2(){return b2;}
    
}
