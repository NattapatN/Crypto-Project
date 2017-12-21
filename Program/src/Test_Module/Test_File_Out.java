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
        program.GetKey getKey = new program.GetKey("text.txt");
        String string = getKey.getA();
        StringBuilder sb = new StringBuilder();
        char[] chars = string.replaceAll("\\s", "").toCharArray();
        int[] mapping = {1, 2, 4, 8, 16, 32, 64, 128};

        for (int j = 0; j < chars.length; j += 8) {
            int idx = 0;
            int sum = 0;
            for (int i = 7; i >= 0; i--) {
                if (chars[i + j] == '1') {
                    sum += mapping[idx];
                }
                idx++;
            }
            System.out.println(sum);//debug
            sb.append(Character.toChars(sum));
        }
        System.out.println(sb.toString());
    }
}
