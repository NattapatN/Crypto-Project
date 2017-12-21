/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto_project;

import Module.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nattapat
 */
public class Decryption {

    int u, p, block;
    String cipherA, cipherB, plain = "";
    PrintWriter writePlain;
    FastExpo fExpo = new FastExpo();
    ExtendedEuclid exGCD = new ExtendedEuclid();
    DectoBi d2b = new DectoBi();
    BittoText b2t = new BittoText();

    public Decryption() {
        ReadKey getKey = new ReadKey("PrivateKey.txt");
        u = Integer.parseInt(getKey.getA());
        p = Integer.parseInt(getKey.getB());
    }

    public boolean decrypt(String file, int b, String fileOut) {
        ReadKey readCipher = new ReadKey(file);
        cipherA = readCipher.getA();
        cipherB = readCipher.getB();
        block = b;

        //decrypr
        String tempA, tempB;
        while (cipherA.length() > b) {
            tempA = cipherA.substring(0, b);
            tempB = cipherB.substring(0, b);
            cipherA = cipherA.substring(b);
            cipherB = cipherB.substring(b);
            goDecypt(Integer.parseInt(tempA, 2), Integer.parseInt(tempB, 2));
        }

        //convert to text
        plain = b2t.getText(plain);

        //writeFile
        if (writeFile(fileOut)) {
            System.out.println("Decryption Complete!!");
        }

        return true;
    }

    private void goDecypt(int ain, int bin) {
        int a, b = bin;
        long temp;

        a = fExpo.getFExpo(ain, u, p);
        exGCD.getGCD(a, p);
        a = exGCD.getA2();
        temp = Math.floorMod(a * b, p);
        b = (int) temp;

        plain = plain + d2b.getBinary(b, block);
    }

    private boolean writeFile(String fileOut) {

        try {
            writePlain = new PrintWriter(fileOut);
            writePlain.println(plain);
            writePlain.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Decryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

}
