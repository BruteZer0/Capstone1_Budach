package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    public static List<Transaction> transactions = new ArrayList<>();

 public static List<Transaction> getTransactions(){
     try {
         BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/transactions.csv"));

         String line;

         while ((line = reader.readLine()) != null) {
             String[] parts = line.split("\\|");

             DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

             LocalDate parsedDate = LocalDate.parse(parts[0], dateFormatter);
             LocalTime parsedTime = LocalTime.parse(parts[1], timeFormatter);
             String description = parts[2];
             String vendor = parts[3];
             double amount = Double.parseDouble(parts[4]);

             Transaction transaction = new Transaction(parsedDate, parsedTime, description, vendor, amount);
             transactions.add(transaction);
         }

         reader.close();

     } catch (IOException e) {
         System.out.println("There was a problem reading the transactions file.");
     }
     catch(Exception ex){
         System.out.println("Something went wrong with the file.");
     }

     return transactions;
 }

 public static void addTransaction(Transaction transaction){
     try{
         File file = new File("src/main/resources/transactions.csv");
         FileWriter fileWriter = new FileWriter(file, true);
         if (file.length() > 0) {
             fileWriter.write(System.lineSeparator());
         }

         fileWriter.write(String.format("%s|%s|%s|%s|%.2f", transaction.getDate(), transaction.getTime(),
                 transaction.getDescription(),transaction.getVendor(),transaction.getAmount()));

         fileWriter.close();
     }
     catch(IOException ex){
         System.out.println("Error writing to file.");
     }
 }

}
