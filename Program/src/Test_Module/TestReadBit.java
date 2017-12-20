/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test_Module;

/**
 *
 * @author NattapatN
 */
public class TestReadBit {
    public static void main(String [] args){
        Module.FileToBit f2b = new Module.FileToBit();
        String sbc = f2b.toBit("test.txt").toString();
        System.out.println(sbc);
    }
    
}
