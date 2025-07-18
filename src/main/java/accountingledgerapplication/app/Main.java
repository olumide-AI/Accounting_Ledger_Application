package accountingledgerapplication.app;

import accountingledgerapplication.model.Transaction;
import accountingledgerapplication.service.Ledger;
import accountingledgerapplication.service.Report;
import accountingledgerapplication.ui.AppHomeScreen;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    //Initialize Scanner at a class level
    static Scanner scanner = new Scanner(System.in);

    // Ledge menu displays that lets user choose their specific type of entry.
    public static void ledgerHomeScreen() {
        boolean flag = true;

        while (flag) {
            System.out.println("Ledger Menu: ");
            System.out.println("Please select the following options");
            System.out.println("A - Display all entries");
            System.out.println("D - Display deposit entries");
            System.out.println("P - Display debit payment entries");
            System.out.println("R - Display custom report screen");
            System.out.println("H - Go back to the Home screen");
            String userInputLedger = scanner.nextLine().toUpperCase().trim();
            switch (userInputLedger) {
                case "A":
                    Ledger.displayAllEntries();
                    break;
                case "D":
                    Ledger.displayDepositEntries();
                    break;
                case "P":
                    Ledger.displayDebitPaymentEntries();
                    break;
                case "R":
                    reportHomeScreen();
                    break;
                case "H":
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter option - A,D,P,R,or H");

            }

        }
    }

    //Reports Screen
    //Displays Report menu based on user input with options for custom or predefined
    public static void reportHomeScreen() {
        boolean flag = true;
        while (flag) {
            System.out.println("Run your custom search on your report");
            System.out.println("Please select the following options");
            System.out.println("1 - Display Month to Date information"); //yyyy-mm-dd
            System.out.println("2 - Display previous month information"); //
            System.out.println("3 - Display year to date information");
            System.out.println("4 - Display previous year information");
            System.out.println("5 - To search by vendor");
            System.out.println("6 - Customize transaction search");
            System.out.println("0 - Go back to the Ledger screen"); //The book says report
            String userInputReport = scanner.nextLine().trim();

            switch (userInputReport) {
                case "1":
                    Report.displayMonthToDateEntries();
                    break;
                case "2":
                    Report.displayPreviousMonthEntries();
                    break;
                case "3":
                    Report.displayYearToDateEntries();
                    break;
                case "4":
                    Report.previousYearEntries();
                    break;
                case "5":
                    Report.searchByVendor(scanner);
                    break;
                case "6":
                    Report.customSearch(scanner);
                    break;
                case "0":
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter option - 1,2,3,4,5, or 0");
            }

        }
    }

    //Add Deposit method
    //use scanner to retrieve info. What info Just deposit? is deposit information just transaction info
    //if so i just create new transaction
    public static Transaction addDepositInformation() {

        return addTransactionDetails(true);
    }

    //Debit payment information (will include "-" price)
    //We can call the writeTransactionsToFile() method to write it into our csv file
    public static Transaction addDebitPaymentInformation() {
        return addTransactionDetails(false);
    }

    //Error handling, Input validation, prompts user to input all necessary transaction fields
    public static Transaction addTransactionDetails(boolean isDeposit) {
        String transactionDate;
        while (true) {
            System.out.println("Enter transaction date (YYYY-MM-DD): ");
            transactionDate = scanner.nextLine().trim();
            try {
                LocalDate.parse(transactionDate);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd (Ex. 2025-05-02) ");
            }
        }
        String transactionTime;
        while(true){
            System.out.println("Enter transaction time in 24hr format (HH:MM): ");
            transactionTime = scanner.nextLine().trim();
            try{
                //Parse date to validate time format
                LocalTime.parse(transactionTime);
                break;
            }
            catch (DateTimeParseException e){
                System.out.println("Invalid time format. Please use 24 hr time clock format (Ex. 14:30)");
            }
        }

        String transactionDescription;
        while (true){
            System.out.println("Enter transaction description: ");
            transactionDescription = scanner.nextLine().trim();
            //transaction description is not empty
            if(!transactionDescription.isEmpty())
            {
                break;
            }
            System.out.println("Description can't be empty.");
        }
        String transactionVendor;
        while(true){
            System.out.println("Enter transaction vendor: ");
            transactionVendor = scanner.nextLine().trim().toLowerCase();
            if(!transactionVendor.isEmpty()){
                break;
            }
            System.out.println("Please provide a vendor name");
        }
        double transactionAmount;
        while(true){
            System.out.println("Enter transaction amount: ");
            try{
                transactionAmount = Double.parseDouble(scanner.nextLine().trim());
                if(transactionAmount <=0){
                    System.out.println("Transaction amount has to be a positive number ");
                }
                else {
                    break;
                }
            }
            catch (NumberFormatException e){
                System.out.println("Amount must be a number, please enter a correct number");
            }
        }
        if (!isDeposit){
            transactionAmount = -1 * transactionAmount;
        }
        System.out.println("Transaction has been added to your transaction file\n");
        return  new Transaction(transactionDate, transactionTime, transactionDescription, transactionVendor,transactionAmount);
    }
}



