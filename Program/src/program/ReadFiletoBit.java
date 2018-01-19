/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author NattapatN
 */
public class ReadFiletoBit {

    private DataInputStream input;

    public StringBuilder read(String frilePath) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(frilePath);
            input = new DataInputStream(new FileInputStream(file));
            try {
                byte mask,x; int index=0;
                while (true) {
                    StringBuilder subsb = new StringBuilder();

                    byte tmp = x = input.readByte();

                    for (int i = 7; i >= 0; --i) {
//                        mask = 1 << i;
                        subsb.append(x >>> i & 1);
                    }
//                    System.out.printf("byte %d (%d) : %s\n", index++, x, subsb.toString());
                    sb.append(subsb);
                    //System.out.println("sub>>"+subsb);
                    //System.out.println(sb);

                }
            } catch (EOFException eof) {
            } catch (IOException e) {
                e.printStackTrace();
            }
            //  System.out.println(sb.toString());
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        return sb;
    }
}
