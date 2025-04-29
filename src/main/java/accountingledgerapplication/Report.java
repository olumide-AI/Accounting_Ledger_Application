package accountingledgerapplication;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Report {

    //Custom search to filter from month to date
    public static void displayMonthToDateEntries(){
        //Reads all file from csv file
        List<Transaction> transactionList = FileHandler.readAllTransactions();
        //Create today's time from system clock
        LocalDate todayDate = LocalDate.now();
        System.out.println("Transactions for this month so far:");
        //For each loop makes it so i don't use get(i) makes code shorter
        for (Transaction transaction : transactionList) {
            if (transaction.getDate().getMonth() == todayDate.getMonth() && transaction.getDate().getYear() == todayDate.getYear()) {
                System.out.println(transaction.displayTransactionFormat());
            }
        }
    }

    //Previous month filter
    //Edge case if jan 2025 - dec 2024
    public static void displayPreviousMonthEntries() {
        List<Transaction> transactionList = FileHandler.readAllTransactions();
        LocalDate todayDate = LocalDate.now();
        //Case to handle if we're in January 2025 and previous year is december 2024
        int previousMonth;
        int yearOfPreviousMonth;
        //int lastMonth = todayDate.minusMonths(1).getMonthValue();
        //If we're in the first month of the year
        if (todayDate.getMonthValue() == 1){
            //we can just set our previous month to decemeber which is 12
            previousMonth = 12;
            //then the year of december will be 2024, last year
            yearOfPreviousMonth = todayDate.getYear() -1; //will give us 2024 in our example
        }
        else {
            //If it's not january of a new year, previous month is just
            //todays month minus 1
            previousMonth = todayDate.getMonthValue() -1;
            yearOfPreviousMonth = todayDate.getYear();
        }
        System.out.println("Transactions for previous month: ");
        for (Transaction transaction : transactionList) {
            if (transaction.getDate().getYear() == yearOfPreviousMonth && transaction.getDate().getMonthValue() == previousMonth) {
                System.out.println(transaction.displayTransactionFormat());
            }
        }
    }

    //year to date filter
    public static void displayYearToDateEntries(){
        List<Transaction> transactionList = FileHandler.readAllTransactions();
        int currentYear = LocalDate.now().getYear();
        System.out.println("Transaction for year " + currentYear);
        for (Transaction transaction : transactionList){
            if (transaction.getDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transaction.displayTransactionFormat());
            }
        }

    }

    //Filter for Previous year
    public static void  previousYearEntries(){
        List<Transaction> transactionList = FileHandler.readAllTransactions();
        //Because date is an integer
        int previousYear = LocalDate.now().getYear() -1;
        System.out.println("Transactions for the last year: ");
        for (Transaction transaction : transactionList){
            if (transaction.getDate().getYear() == previousYear){
                System.out.println(transaction.displayTransactionFormat());
            }
        }
    }

    //Custom search by vendor name
    //Use .equals or if a line contains this user input print it out
    public static void searchByVendor(Scanner scanner){
        System.out.println("What is the vendor name to search: ");
        String userVendorName = scanner.nextLine().toLowerCase();
        List<Transaction> transactionList = FileHandler.readAllTransactions();
        System.out.println("Here is all the transaction entries for" + userVendorName + " : ");
        for (Transaction transaction : transactionList) {
            if (transaction.getVendor().toLowerCase().contains(userVendorName)) {
                System.out.println(transaction.displayTransactionFormat());
            }
        }

    }
}
