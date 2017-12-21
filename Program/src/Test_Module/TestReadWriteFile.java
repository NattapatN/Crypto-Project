/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author NattapatN
 */
public class TestReadWriteFile {
    public static void main(String [] args){
        try (
                InputStream inputStream = new FileInputStream("test.txt");
                OutputStream outputStream = new FileOutputStream("test2.txt");
                ) {

            long fileSize = new File("test.txt").length();

            byte[] allBytes = new byte[(int) fileSize];
            
            inputStream.read(allBytes);
            StringBuilder  sb = new StringBuilder();
            for (int b = 0; b < allBytes.length; b++) {
                sb.append(String.format("%8s", Integer.toBinaryString(allBytes[b] & 0xFF)).replace(' ', '0'));

            }
            byte[] byteOut = new byte[Math.floorMod(sb.length(), 8)];
            String a=sb.toString();
            String temp;
            for(int b=0;b<byteOut.length;b++){
                if(a.substring(0,1).equals("1"))temp="-"+a.substring(1,8);
                byteOut[b]=Byte.parseByte(a,2);
                
            }
            
            outputStream.write(byteOut);
            System.out.println(sb);
            System.out.println(allBytes.length);
            //System.out.println(allBytes);
            System.out.println(byteOut[0]);
            System.out.println(allBytes[0]);
            System.out.println(byteOut.length);
//            StringBuilder  sbb = new StringBuilder();
//            for (int b = 0; b < byteOut.length; b++) {
//
//                sbb.append(String.format("%8s", Integer.toBinaryString(byteOut[b] & 0xFF)).replace(' ', '0'));
//
//            }
//            System.out.println(sbb);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
