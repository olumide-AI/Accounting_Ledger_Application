package accountingledgerapplication;

import java.time.LocalDate;
import java.util.List;

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
        System.out.println("Transactions for previous month: ");
        for (Transaction transaction : transactionList) {
            if (transaction.getDate().getMonth() == todayDate.getMonth().minus(1) && transaction.getDate().getYear() == todayDate.getYear()) {
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
}
