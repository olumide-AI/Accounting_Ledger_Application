package accountingledgerapplication;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        //Create a main Home screen
        boolean flag = true;
        while (flag) {
            System.out.println("Welcome to the Home screen");
            System.out.println("Please select the following options");
            System.out.println("D - Add deposit information");
            System.out.println("P - Make debit payment");
            System.out.println("L - Display ledger screen");
            System.out.println("E - Exit the application");
            String userInputHomeScreen = scanner.nextLine().toUpperCase().trim();
            switch (userInputHomeScreen) {
                case "D":
                    Transaction userDeposit = addDepositInformation();
                    FileHandler.writeTransactionsToFile(userDeposit);
                    break;
                case "P":
                    Transaction userDebitPayment = addDebitPaymentInformation();
                    FileHandler.writeTransactionsToFile(userDebitPayment);
                    break;
                case "L":
                    ledgerHomeScreen();
                    break;
                case "E":
                    System.out.println("Thank you, goodbye");
                    ;//Exit
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter option D,P,L or E");
            }
        }
        //Closed my scanner at the end of main
        scanner.close();
    }

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

    //Error handling, Input validation
    public static Transaction addTransactionDetails(boolean isDeposit) {
        String transactionDate;
        while (true) {
            System.out.println("Enter transaction date (YYYY-MM-DD): ");
            transactionDate = scanner.nextLine().trim();
            try {
                LocalDate.parse(transactionDate);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd");
            }
        }
        System.out.println("Enter transaction time  (HH:MM): ");
        String transactionTime = scanner.nextLine().trim();

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
        System.out.println("Transaction has been added to your transaction file");
        return  new Transaction(transactionDate, transactionTime, transactionDescription, transactionVendor,transactionAmount);
    }

}
