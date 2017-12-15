/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

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
        switch(in){
            case 1: Genkey genk = new Genkey();
                    //System.out.println("getKey");
                    genk.genK();
                    p=genk.getP();
                    g=genk.getG();
                    y=genk.getY();
                    u=genk.getU();
                    break;
            case 2: Encryption encrypt = new Encryption(p,g,y);
                    encrypt.Encrypt(24);
                    cipherA= encrypt.getCipherA();
                    cipherB= encrypt.getCipherB();
                    break;
            case 3: Decryption decrypt = new Decryption(u,p);
                    decrypt.getDecrypt(cipherA, cipherB, 24);
                    break;
            case 4: 
                    break;
            case 5: 
                    break;
            case 6:
                    break;
            default :
                System.out.println("--------------------------------------------------");
                System.out.println("\t\tProgram Ended.");
                System.out.println("--------------------------------------------------");
            
        }
    }
    
}
