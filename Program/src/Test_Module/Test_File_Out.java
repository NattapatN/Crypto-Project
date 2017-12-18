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
        StringBuilder sb = new StringBuilder();
        program.DectoBinary d2b = new program.DectoBinary();

        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream("test.txt"))) {
            for (int b; (b = is.read()) != -1;) {
                String s = Integer.toString(b);
                int mask;
                for (int i = 7; i >= 0; i--) {
                    mask = 1 << i;
                    sb.append((b & mask) != 0 ? "1" : "0");
                }
            }
        }
        System.out.println(sb);
    }
}
