package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> products = TransactionManager.getTransactions();

        while (true) {
            System.out.println("\n=== BANK OF AMERICA DEBIT CARD MENU ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");


            char menuChoice = getValidChar(scanner);

            switch(menuChoice) {
                case 'D':
                    System.out.println("\n=== MAKE DEPOSIT ===");

                case 'P':
                    System.out.println("\n=== MAKE PAYMENT  ===");

                case 'L':
                    System.out.println("\n=== LEDGER ===");
                    System.out.println("A) All");
                    System.out.println("D) Deposits");
                    System.out.println("P) Payments");
                    System.out.println("R) Reports");
                    System.out.println("H) Home");
                    System.out.print("Choose an option: ");

                    char ledgerChoice = getValidChar(scanner);

                    switch(ledgerChoice) {
                        case 'A':
                        case 'D':
                        case 'P':
                        case 'R':
                        case 'H':
                            break;

                    }


                case 'X':
                    System.out.println("Exiting system");
                    System.exit(0);

            }


        }

    }

    public static char getValidChar(Scanner scanner) {
        while (true) {
            String input = scanner.next().trim();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return Character.toUpperCase(input.charAt(0));
            }

            System.out.print("Invalid input. Enter a single letter: ");
        }
    }

    public
}