/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author NattapatN
 */
public class Menu {
    int p,g,y,u;
    String cipherA,cipherB;
    String textA,textB;
    Scanner scan = new Scanner(System.in);
    public Menu(){
        int menu;
        do{
        menuDetail();
        menu =scan.nextInt(); 
        menu = checkMenu(menu);
        gotoMenu(menu);
        }while(menu!=0);
    }
    
    private void menuDetail(){
        //Menu
        System.out.println("Main Menu..");
        System.out.println("1. Generate key");
        System.out.println("2. Encryption");
        System.out.println("3. Edcryption");
        System.out.println("4. Signature");
        System.out.println("5. Verify");
        System.out.println("6. Cryptography Hash");
        System.out.println("0. Exit Program");
        //get input menu
        System.out.print("Enter number of menu : ");
    }
    
    private int checkMenu(int inn){
        int in=inn;
        boolean check=false;
        while (!check){
            if(in>=1&&in<=6){
                check = true;
            }
            else if(in==0){check=true;}
            else{
                System.out.println("Menu number "+in+" incorrect!!");
                System.out.print("Re-enter number of menu : ");
                in = scan.nextInt();
            }
        }
        return in;
    }
    
    private void gotoMenu(int in){
        //System.out.println("gotoMenu");
        String a;
        switch(in){
            case 1: System.out.println("--------------------------------------------------");
                    Genkey genk = new Genkey();
                    //System.out.println("getKey");
                    genk.genK();
                    System.out.println("--------------------------------------------------");
                    break;
            case 2: System.out.println("--------------------------------------------------");
                    Encryption encrypt = new Encryption();
                    System.out.print("Enter Filename (Plaintext) : ");
                    a= scan.next();
                    encrypt.Encrypt(a,24);
                    System.out.println("--------------------------------------------------");
                    break;
            case 3: System.out.println("--------------------------------------------------");
                    Decryption decrypt = new Decryption();
                    System.out.print("Enter Filename (Ciphertext) : ");
                    a= scan.next();
                    decrypt.getDecrypt(a,24);
                    System.out.println("--------------------------------------------------");
                    break;
            case 4: System.out.println("--------------------------------------------------");
                    System.out.println("--------------------------------------------------");
                    break;
            case 5: System.out.println("--------------------------------------------------");
                    System.out.println("--------------------------------------------------");
                    break;
            case 6:
                    System.out.println("--------------------------------------------------");
                    System.out.println("--------------------------------------------------");
                    break;
            default :
                System.out.println("--------------------------------------------------");
                System.out.println("\t\tProgram Ended.");
                System.out.println("--------------------------------------------------");
            
        }
    }
    
}
