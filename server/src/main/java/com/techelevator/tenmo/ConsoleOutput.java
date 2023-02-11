package com.techelevator.tenmo;

import java.util.Scanner;

public class ConsoleOutput {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("----TEnmo Main Menu----");
        System.out.println();
        System.out.println("1: Login");
        System.out.println("2: Exit");
        System.out.println();
    }



    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }


//    register with user and password
/* see account balance
send a transfer of specific amount
choose list of users
not send money to yourself
sending transfer has status of approved
see transfers sent and received
retrieve details of transfers


 */

}
