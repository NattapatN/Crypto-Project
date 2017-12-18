/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Module;

import java.io.*;
/**
 *
 * @author NattapatN
 */
public class Test_File_Out {

    public static void main(String[] args) throws IOException {
        program.GetKey getKey = new program.GetKey("PrivateKey.txt");
        System.out.println(getKey.getA());
        System.out.println(getKey.getB());
        System.out.println(getKey.getC());
    }
}
