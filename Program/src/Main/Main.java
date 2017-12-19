/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Scanner;

/**
 *
 * @author NattapatN
 */
public class Main {
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String [] args){
        
        int menu;
        do{
        menuDetail();
        menu = scan.nextInt();
        menu = checkMenu(menu);
        gotoMenu(menu);
        }while(menu!=0);
    }
    
    private static void menuDetail(){
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
    
     private static int checkMenu(int inn){
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
     
     private static void gotoMenu(int in){
        //System.out.println("gotoMenu");
        switch(in){
            case 1: System.out.println("--------------------------------------------------");
                    System.out.println("[Generate key]");
                    System.out.print("Enter Filename : ");
                    String filename = scan.next();
                    System.out.print("Enter Key Size (<32 bit) : ");
                    int kSize = scan.nextInt();
                    GenKey genKey =new GenKey(filename,kSize);
                    System.out.println("--------------------------------------------------");
                    break;
            case 2: System.out.println("--------------------------------------------------");
                    System.out.println("[Encryption]");
                    System.out.println("--------------------------------------------------");
                    break;
            case 3: System.out.println("--------------------------------------------------");
                    System.out.println("[Edcryption]");
                    System.out.println("--------------------------------------------------");
                    break;
            case 4: System.out.println("--------------------------------------------------");
                    System.out.println("[Signature]");
                    System.out.println("--------------------------------------------------");
                    break;
            case 5: System.out.println("--------------------------------------------------");
                    System.out.println("[Verify]");
                    System.out.println("--------------------------------------------------");
                    break;
            case 6:
                    System.out.println("--------------------------------------------------");
                    System.out.println("[Cryptography Hash]");
                    System.out.println("--------------------------------------------------");
                    break;
            default :
                System.out.println("--------------------------------------------------");
                System.out.println("\t\tProgram Ended.");
                System.out.println("--------------------------------------------------");
            
        }
    }
    
}
