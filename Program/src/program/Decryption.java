/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import static program.Encryption.block;

/**
 *
 * @author NattapatN
 */
public class Decryption {
    int u,p,block;
    static DectoBinary d2b = new DectoBinary();
    static ExtendedEuclidGCD xuGCD = new ExtendedEuclidGCD();
    FastExponential fExpo = new FastExponential();
    public Decryption(int uin,int pin){
        u=uin;
        p=pin;
    }
    
    public void getDecrypt(String ain,String bin,int bl){
        block=bl;
        String a=ain+"0";
        String b=bin+"0";
        String tempA="",tempB="";
        int ciA,ciB;
        int textBl;
        String text="";
        int i=0;
        boolean eof = false;
        while(!eof){
            if(i!=0&&i%block==0){
                ciA= Integer.parseInt(tempA, 2);
                ciB= Integer.parseInt(tempB, 2);
                System.out.println("A : "+ciA+" B : "+ciB);
                textBl = goDecrypt(ciA,ciB);
                text = text+textBl;
                i=0;
                ciA=0;
                ciB=0;
                tempA="";
                tempB="";
            }
            if (a.length()>1) {
                //add string to tempA & tempB
                tempA = tempA + a.substring(0, 1);
                tempB = tempB + b.substring(0, 1);
                //substring
                a = a.substring(1);
                b = b.substring(1);
            }
            else{
                tempA = tempA + a;
                tempB = tempB + b;
                eof=true;
            }
            i++;
        }
        System.out.println("text : "+text);
    }
    private int goDecrypt(int ain,int bin){
        int a=ain;
        int b= bin;
        long x,invertA;
        a=fExpo.getFastExpo(a,u,p);
        invertA=xuGCD.getGCD(a, p);
        invertA=xuGCD.getA2();
        System.out.println("Invert A : "+invertA);
        x=Math.floorMod(b*invertA, p);
        return (int)x;
    }
    
}
