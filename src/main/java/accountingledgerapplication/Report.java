package accountingledgerapplication;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
            //we can just set our previous month to Dec which is 12
            previousMonth = 12;
            //then the year of december will be 2024, last year
            yearOfPreviousMonth = todayDate.getYear() -1; //will give us 2024 in our example
        }
        else {
            //If it's not january of a new year, previous month is just
            //today's month minus 1
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
    // Method for custom search
    //Do i create a custom search method
    public static void customSearch(Scanner scanner){
        System.out.println("Custom Search: ");
        System.out.println("By Start Date (YYYY-MM-DD): ");
        String userStartDate = scanner.nextLine().trim();

        System.out.println("By End Date (YYYY-MM-DD): ");
        String userEndDate = scanner.nextLine().trim();

        System.out.println("By Description: ");
        String userDescription = scanner.nextLine().trim();

        System.out.println("By Vendor Name: ");
        String userVendor = scanner.nextLine().trim();

        System.out.println("By Transaction amount: ");
        String userAmount = scanner.nextLine().trim();

        //Load all transaction from csv file into a list
        List<Transaction> transactionList = FileHandler.readAllTransactions();

        System.out.println("Custom Search Results: ");
        //Search through all transactions one by one
        for (Transaction currentTransaction: transactionList){
            //We only want to print when transaction if it passes all filters
            //
            boolean flag = true;

            //Start Date checks
            //Check if user type a start date, if not don't execute
            if (!userStartDate.isEmpty()){
                try{
                    //Convert string from string to localdate for comparison
                    LocalDate startDate = LocalDate.parse(userStartDate);
                    //If transactoin is older thsn the start date, don't add to our list
                    if (currentTransaction.getDate().isBefore(startDate)){
                        flag = false;
                }

                }
                catch (DateTimeParseException e){
                    System.out.println("Wrong start date format. This filter won't be added to your custom filter");
                }
            }

            //End Date checks
            //The if block will be skipped if user left it empty string
            if (!userEndDate.isEmpty()){
                try{
                    LocalDate endDate = LocalDate.parse(userEndDate);
                    //Is the transaction date later than the user end date
                    //If it's greater it's false anf the code skips it
                    if (currentTransaction.getDate().isAfter(endDate)){
                        flag = false;
                    }
                }
                catch (DateTimeParseException e){
                    System.out.println("Wrong end date format. This filter won't be added to your custom filter. ");
                }
            }

            //Start date and end date will create a window
            //show mw transaction between january and July

            //Description check
            //Is there description in this start and end date if any matches lets keep going with it
            //Make sure we convert to lowercase to be safe
            //Skip it all together if user leaves the scanner empty
            //We can use .contains() or .isequals()
            if (!userDescription.isEmpty() && (!currentTransaction.getDescription().toLowerCase().contains(userDescription))){
                flag = false;
            }

            //Vendor checks
            if (!userVendor.isEmpty() && (!currentTransaction.getVendor().toLowerCase().contains(userVendor))){
                flag = false;
            }

            if (!userAmount.isEmpty()){
                try{
                    double searchAmount = Double.parseDouble(userAmount);
                    if (currentTransaction.getAmount() != searchAmount){
                        flag = false;
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Please enter a correct number amount. Filter isn't added to your custom search");
                }
            }



        }





    }
}
