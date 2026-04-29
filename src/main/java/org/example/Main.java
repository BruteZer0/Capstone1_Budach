package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        List<Transaction> transactions = TransactionManager.getTransactions();

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Show Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");

            char menuChoice = getValidChar(scanner);

            switch(menuChoice) {
                case 'D':
                    deposit(transactions);
                    break;

                case 'P':
                    payment(transactions);
                    break;

                case 'L':
                     ledger(transactions);
                     break;

                case 'X':
                    mainMenuExit(transactions);
                    break;
            }
        }
    }

    public static void deposit(List<Transaction> transactions){
        System.out.println("\n=== MAKE DEPOSIT ===");
    }

    public static void payment(List<Transaction> transactions){
        System.out.println("\n=== MAKE PAYMENT  ===");
    }

    public static void ledger(List<Transaction> transactions){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("\n=== LEDGER ===");
            System.out.println("A) All");
            System.out.println("D) Show Deposits");
            System.out.println("P) Show Payments");
            System.out.println("R) Show Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            char ledgerChoice = getValidChar(scanner);

            switch (ledgerChoice) {
                case 'A':
                    System.out.println("\nShowing Entries:");
                    displayTransactions(transactions);
                    break;
                case 'D':
                    System.out.println("\nShowing Entries:");
                    displayDeposits(transactions);
                    break;
                case 'P':
                    System.out.println("\nShowing Entries:");
                    displayPayments(transactions);
                    break;
                case 'R':
                    reports(transactions);
                    break;
                case 'H':
                    return;
            }
        }
    }

    public static void mainMenuExit(List<Transaction> transactions) throws InterruptedException {
        System.out.println("\nExiting system...");
        Thread.sleep(1000);
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public static void reports(List<Transaction> transactions){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== REPORTS ===");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search By Vendor");
            System.out.println("6) Back");
            System.out.print("Choose an option: ");

            int reportChoice = getValidInt(scanner);

            switch (reportChoice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("\nShowing Vendors:");
                    displayVendors(transactions);
                    System.out.print("\nType Vendor Name: ");
                    String vendorName = scanner.nextLine();
                    System.out.println("\nShowing Entries:");
                    for (Transaction transaction : transactions) {
                        if (Objects.equals(transaction.getVendor(), vendorName)) {
                            System.out.println(transaction.toString());
                        }
                    }
                    break;
                case 6:
                    return;
            }
        }
    }

    public static char getValidChar(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return Character.toUpperCase(input.charAt(0));
            }

            System.out.print("Invalid input. Enter a single letter: ");
        }
    }//validation input for character input

    public static int getValidInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }//validation input for integer input

    public static void displayTransactions(List<Transaction> transactions){
        for(Transaction transaction: transactions)
            System.out.println(transaction.toString());
    }

    public static void displayDeposits(List<Transaction> transactions){
        for(Transaction transaction: transactions){
            if(transaction.getAmount() > 0){
                System.out.println(transaction);
            }
        }
    }

    public static void displayPayments(List<Transaction> transactions){
        for(Transaction transaction: transactions){
            if(transaction.getAmount() < 0){
                System.out.println(transaction);
            }
        }
    }

    public static void displayVendors(List<Transaction> transactions){
        List<String> seen = new ArrayList<>();

        for (Transaction transaction : transactions) {
            String vendor = transaction.getVendor();

            if (!seen.contains(vendor)) {
                seen.add(vendor);
                System.out.println(vendor);
            }
        }
    }
}