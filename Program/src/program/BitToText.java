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
public class BitToText {
    public BitToText(){}
    
    public String getText(String text){
        StringBuilder sb = new StringBuilder();
        char[] chars = text.replaceAll("\\s", "").toCharArray();
        int[] mapping = {1, 2, 4, 8, 16, 32, 64, 128};

        for (int j = 0; j < chars.length; j += 8) {
            int idx = 0;
            int sum = 0;
            for (int l = 7; l>= 0; l--) {
                if (chars[l + j] == '1') {
                    sum += mapping[idx];
                }
                idx++;
            }
            sb.append(Character.toChars(sum));
        }
        
        return sb.toString();
    }
}
