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
        //For each loop makes it so i don't use get(i) makes code shorter
        for (Transaction transaction : transactionList) {
            if (transaction.getDate().getMonth() == todayDate.getMonth() && transaction.getDate().getYear() == todayDate.getYear()) {
                System.out.println(transaction.displayTransactionFormat());
            }
        }
    }

    
}
