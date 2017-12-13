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
    Scanner scan = new Scanner(System.in);
    public Menu(){
        menuDetail();
        int menu ; 
        checkMenu();
        //gotoMenu(menu);
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
        
        //get input menu
        System.out.print("Enter number of menu : ");
    }
    
    private void checkMenu(){
        int in=scan.nextInt();
        /*boolean check=true;
        while (check){
            if(in>=1&&in<=6){
                check = false;
                System.out.println("yes!!");
            }
            else{
                System.out.println("Menu number "+in+" incorrect!!");
                System.out.print("Re-enter number of menu : ");
                in = scan.nextInt();
            }
        }*/
        gotoMenu(in);
    }
    
    private void gotoMenu(int in){
        //System.out.println("gotoMenu");
        switch(in){
            case 1: Genkey genk = new Genkey();
                    //System.out.println("getKey");
                    genk.genK();
                    break;
            case 2: 
                    break;
            case 3: 
                    break;
            case 4: 
                    break;
            case 5: 
                    break;
            default :
            
        }
    }
    
}
