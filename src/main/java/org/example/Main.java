package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> transactions = TransactionManager.getTransactions();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");


            char menuChoice = getValidChar(scanner);

            switch(menuChoice) {
                case 'D':
                    System.out.println("\n=== MAKE DEPOSIT ===");
                    break;

                case 'P':
                    System.out.println("\n=== MAKE PAYMENT  ===");
                    break;

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
                            displayTransactions(transactions);
                            break;
                        case 'D':
                        case 'P':
                        case 'R':
                        case 'H':
                            break;

                    }
                    break;



                case 'X':
                    System.out.println("\nExiting system");
                    Thread.sleep(1000);
                    System.out.println("Goodbye");
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

    public static void displayTransactions(List<Transaction> transactions){
        for(Transaction transaction: transactions)
            System.out.println(transaction.toString());
    }
}