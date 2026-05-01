package org.example;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        Scanner scanner = new Scanner(System.in);
        List<Transaction> transactions = TransactionManager.getTransactions();

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Show Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose Option: ");

            char menuChoice = getValidChar(scanner);

            switch(menuChoice) {
                case 'D':
                    deposit(transactions, scanner);
                    break;

                case 'P':
                    payment(transactions, scanner);
                    break;

                case 'L':
                    ledger(transactions, scanner);
                    break;

                case 'X':
                    mainMenuExit();
                    return;

                default:
                    System.out.println("Invalid Option");
            }
        }
    }

    public static void deposit(List<Transaction> transactions, Scanner scanner){
        System.out.println("\n=== MAKE DEPOSIT ===");

        System.out.print("Deposit From: ");
        String vendor = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        LocalDate date;
        LocalTime time;

        System.out.println("\n=== DATE/TIME ===");
        System.out.println("1) Manual Entry");
        System.out.println("2) Current Date/Time");
        System.out.print("Choose Option: ");

        int choice = getValidInt(scanner);


        switch (choice) {
            case 1:
                while(true) {
                    try {
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        date = LocalDate.parse(scanner.nextLine().trim());

                        System.out.print("Enter Time (HH:MM:SS): ");
                        time = LocalTime.parse(scanner.nextLine().trim());

                        break;
                    } catch (DateTimeException e) {
                        System.out.println("Invalid Input: Please Try Again");
                    }
                }
                break;

            case 2:
                date = LocalDate.now();
                time = LocalTime.now().withNano(0);
                break;

            default:
                System.out.println("Invalid Option: Using Current Date/Time.");
                date = LocalDate.now();
                time = LocalTime.now().withNano(0);

        }

        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        transactions.add(transaction);

        TransactionManager.addTransaction(transaction);

        System.out.println("\nDeposit Successfully Added!");
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Description: " + description);
        System.out.println("From: " + vendor);
        System.out.println("Amount: $" + amount);
    }

    public static void payment(List<Transaction> transactions, Scanner scanner){
        System.out.println("\n=== MAKE PAYMENT  ===");

        System.out.print("Debit Card Info: ");

        String debit = scanner.nextLine();

        System.out.print("Payment To: ");
        String vendor = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -Math.abs(amount);

        LocalDate date;
        LocalTime time;

        System.out.println("\n=== DATE/TIME ===");
        System.out.println("1) Manual Entry");
        System.out.println("2) Current Date/Time");
        System.out.print("Choose Option: ");

        int choice = getValidInt(scanner);

        switch (choice) {
            case 1:
                while(true) {
                    try {
                        System.out.print("Enter Date (YYYY-MM-DD): ");
                        date = LocalDate.parse(scanner.nextLine().trim());

                        System.out.print("Enter Time (HH:MM:SS): ");
                        time = LocalTime.parse(scanner.nextLine().trim());

                        break;
                    } catch (DateTimeException e) {
                        System.out.println("Invalid Input: Please Try Again");
                    }
                }
                break;

            case 2:
                date = LocalDate.now();
                time = LocalTime.now().withNano(0);
                break;

            default:
                System.out.println("Invalid Option: Using Current Date/Time.");
                date = LocalDate.now();
                time = LocalTime.now().withNano(0);

        }

        Transaction transaction = new Transaction(date, time, description, vendor, amount);

        transactions.add(transaction);

        TransactionManager.addTransaction(transaction);

        System.out.println("\nPayment Successfully Added!");
        System.out.println("Debit Card:" + debit);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Description: " + description);
        System.out.println("From: " + vendor);
        System.out.println("Amount: $" + amount);
    }

    public static void ledger(List<Transaction> transactions, Scanner scanner){
        while(true) {
            System.out.println("\n=== LEDGER ===");
            System.out.println("A) All");
            System.out.println("D) Show Deposits");
            System.out.println("P) Show Payments");
            System.out.println("R) Show Reports");
            System.out.println("H) Home");
            System.out.print("Choose Option: ");

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
                    reports(transactions, scanner);
                    break;
                case 'H':
                    return;
            }
        }
    }

    public static void mainMenuExit() throws InterruptedException {
        System.out.println("\nExiting System...");
        Thread.sleep(1000);
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public static void reports(List<Transaction> transactions, Scanner scanner){
        while (true) {
            System.out.println("\n=== REPORTS ===");
            System.out.println("1) Month to Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search By Vendor");
            System.out.println("6) Back");
            System.out.print("Choose Option: ");

            int reportChoice = getValidInt(scanner);

            switch (reportChoice) {
                case 1:
                    searchMonthToDate(transactions);
                    break;
                case 2:
                    searchPreviousMonth(transactions);
                    break;
                case 3:
                    searchYearToDate(transactions);
                    break;
                case 4:
                    searchPreviousYear(transactions);
                    break;
                case 5:
                    searchByVendor(transactions, scanner);
                    break;
                case 6:
                    return;
            }
        }
    }

    public static void searchMonthToDate(List<Transaction> transactions){
        LocalDate today = LocalDate.now();
        LocalDate firstDayMonth = today.withDayOfMonth(1);

        boolean found = false;

        System.out.println("\n=== MONTH TO DATE ===");

        for (Transaction transaction: transactions){
            LocalDate date = transaction.getDate();

            if(!date.isBefore(firstDayMonth) && !date.isAfter(today)) {
                System.out.println(transaction);
                found = true;

            }
        }
        if (!found){
            System.out.println("No Month to Date Entries Found");
        }
    }

    public static void searchPreviousMonth(List<Transaction> transactions){
        LocalDate today = LocalDate.now();
        LocalDate previousMonth = today.minusMonths(1);

        LocalDate startDate = previousMonth.withDayOfMonth(1);
        LocalDate endDate = previousMonth.withDayOfMonth(previousMonth.lengthOfMonth());

        boolean found = false;

        System.out.println("\n=== PREVIOUS MONTH ===");

        for (Transaction transaction : transactions) {

            LocalDate date = transaction.getDate();

            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Previous Month Entries Found");
        }
    }

    public static void searchYearToDate(List<Transaction> transactions){
        LocalDate today = LocalDate.now();
        LocalDate firstDayYear = today.withDayOfYear(1);

        boolean found = false;

        System.out.println("\n=== YEAR TO DATE ===");

        for (Transaction transaction: transactions){
            LocalDate date = transaction.getDate();

            if(!date.isBefore(firstDayYear) && !date.isAfter(today)) {
                System.out.println(transaction);
                found = true;

            }
        }
        if (!found){
            System.out.println("No Year to Date Entries Found");
        }
    }

    public static void searchPreviousYear(List<Transaction> transactions){
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minusYears(1);

        LocalDate startDate = previousYear.withDayOfYear(1);
        LocalDate endDate = previousYear.withDayOfYear(previousYear.lengthOfYear());

        boolean found = false;

        System.out.println("\n=== PREVIOUS YEAR ===");

        for (Transaction transaction : transactions) {

            LocalDate date = transaction.getDate();

            if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
                System.out.println(transaction);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Previous Year Entries Found");
        }
    }

    public static void searchByVendor(List<Transaction> transactions, Scanner scanner){
        System.out.println("\nShowing Vendors:");
        displayVendors(transactions);
        System.out.print("\nType Vendor Name: ");
        String vendorName = scanner.nextLine().trim();
        boolean found = false;
        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                if (!found) {
                    System.out.println("\nShowing Entries:");
                    found = true;
                }
                System.out.println(transaction);
            }
        }
        if(!found){
            System.out.println("\nVendor Not Found");
        }
    }

    public static char getValidChar(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return Character.toUpperCase(input.charAt(0));
            }

            System.out.print("Invalid Input: Enter a Single Letter: ");
        }
    }//validation input for character input

    public static int getValidInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input: Please Enter a Number.");
            }
        }
    }//validation input for integer input

    public static void displayTransactions(List<Transaction> transactions){
        transactions.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime));
        for(Transaction transaction: transactions)
            System.out.println(transaction.toString());
    }

    public static void displayDeposits(List<Transaction> transactions){
        transactions.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime));
        for(Transaction transaction: transactions){
            if(transaction.getAmount() > 0){
                System.out.println(transaction);
            }
        }
    }

    public static void displayPayments(List<Transaction> transactions){
        transactions.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime));
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