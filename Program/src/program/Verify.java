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
public class Verify {
    int g,p,y;
    int r,s;
    int hash,CipherHash;
    FastExponential fExpo = new FastExponential();
    
    public Verify(){}
    
    public boolean verif(String sign,String h){
        //get key
        GetKey getPk =new GetKey("PublicKey.txt");
        p=Integer.parseInt(getPk.getA());
        g=Integer.parseInt(getPk.getB());
        y=Integer.parseInt(getPk.getC());
        
        //get sign
        GetKey getSign =new GetKey(sign); 
        r= Integer.parseInt(getSign.getA());
        s= Integer.parseInt(getSign.getB());
        
        //get hash
        GetKey getHash =new GetKey(h); 
        hash= Integer.parseInt(getHash.getA());
        
        //compare
        int a = fExpo.getFastExpo(g,hash,p);
        int tempy = fExpo.getFastExpo(y,r,p);
        int tempr = fExpo.getFastExpo(r,s,p);
        long b = Math.floorMod((long)tempy*tempr, p);
        System.out.println("a : "+a);
        System.out.println("b : "+b);
        if (a == (int) b) {
            return true;
        } else {
            return false;
        }
    
    }
    
}
