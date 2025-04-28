package accountingledgerapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Create a main Home screen
        while (true) {
            System.out.println("Welcome to the Home screen");
            System.out.println("Please select the following options");
            System.out.println("D - Add deposit information");
            System.out.println("P - Make debit payment");
            System.out.println("L - Display ledger screen");
            System.out.println("E - Exit the application");
            String userInputHomeScreen = scanner.nextLine().toUpperCase().trim();
            switch (userInputHomeScreen) {
                case "D":
                    ;//Add deposit method
                    break;
                case "P":
                    ;//Make payment method
                    break;
                case "L":
                    ;//Display ledger
                    break;
                case "E":
                    System.out.println("Thank you, goodbye");
                    ;//Exit
                    break;
                default:
                    System.out.println("Please enter option D,P,L or X");
            }
        }
    }

    public static void ledgerHomeScreen(Scanner scanner) {
        /*
                    A) All - Display all entries
            o D) Deposits - Display only the entries that are deposits into the
            account
            o P) Payments - Display only the negative entries (or payments)
            o R) Reports - A new screen that allows the user to run pre-defined
            reports or to run a custom search
            § 1) Month To Date
            § 2) Previous Month
            § 3) Year To Date
            § 4) Previous Year
            § 5) Search by Vendor - prompt the user for the vendor name
            and display all entries for that vendor
            § 0) Back - go back to the report page
            o H) Home - go back to the home page
         */
        while (true) {
            System.out.println("Display all entries");
            System.out.println("Please select the following options");
            System.out.println("A - Display all entries");
            System.out.println("D - Display deposit information");
            System.out.println("P - Display debit payment");
            System.out.println("R - Display custom report screen");
            System.out.println("H - Go back to the Home screen");
            String userInputLedger = scanner.nextLine().toUpperCase().trim();
            switch (userInputLedger) {
                case "A":
                    ;//Include all entries display
                    break;
                case "D":
                    ;//Display entries deposited into the account
                    break;
                case "P":
                    ;//Display payments(negative entries)
                    break;
                case "R":
                    ;//New Screen Method
                    break;
                case "H":
                    ;//Go back to the home page
                    break;
                default:
                    System.out.println("Please enter option - A,D,P,R,or H");

            }

        }
    }

    //Reports Screen
    public static void reportHomeScreen(Scanner scanner) {
        while (true) {
            System.out.println("Run your custom search on your report");
            System.out.println("Please select the following options");
            System.out.println("1 - Display Month to Date information");
            System.out.println("2 - Display previous month information");
            System.out.println("3 - Display year to date information");
            System.out.println("4 - Display previous year information");
            System.out.println("5 - To search by vendor");
            System.out.println("0 - Go back to the Ledger screen"); //The book says report
            int userInputReport = scanner.nextInt();
            scanner.nextLine();
            switch (userInputReport) {
                case 1:
                    ;//Month to date report
                    break;
                case 2:
                    ;//previous month report
                    break;
                case 3:
                    ;//year to date report
                    break;
                case 4:
                    ;//previous year report
                    break;
                case 5:
                    ;//Vendor specific report
                    break;
                case 0:
                    ;//Go back to report or ledger page?
                default:
                    System.out.println("Please enter option - 1,2,3,4,5, or 0");
            }

        }
    }

    //Add Deposit method
    //use scanner to retrieve info. What info Just deposit? is deposit information just transaction info
    //if so i just create new transaction
    public static Transaction addDepositInformation() {
        System.out.println("What date is your transaction: ");
        String date = scanner.nextLine();
        System.out.println("What time was your transaction: ");
        String time = scanner.nextLine();
        System.out.println("What is your transaction description: ");
        String description = scanner.nextLine();
        System.out.println("What is your transaction vendor name: ");
        String vendor = scanner.nextLine();
        System.out.println("What was the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        return new Transaction(date, time, description, vendor, amount);
    }
    //make a method for debit transaction (-) while returning

    //Write the user deposit information to file
    public static void writeTransactionsToFile(List<Transaction> transactionEntry) {
        try (FileWriter fileWriter = new FileWriter("transactionFolder/transaction.csv", true)) {
            for (int i = 0; i < transactionEntry.size(); i++) {
                fileWriter.write(transactionEntry.get(i).displayTransactionFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Oh no: " + e.getMessage());
        }
    }

    //Debit payment information (will include "-" price)
    //We can call the writeTransactionsToFile() method to write it into our csv file
    public static Transaction addDebitPaymentInformation() {
        System.out.println("What date is your transaction: ");
        String date = scanner.nextLine();
        System.out.println("What time was your transaction: ");
        String time = scanner.nextLine();
        System.out.println("What is your transaction description: ");
        String description = scanner.nextLine();
        System.out.println("What is your transaction vendor name: ");
        String vendor = scanner.nextLine();
        System.out.println("What was the amount (with a negative for debit ): ");
        double amount = Double.parseDouble(scanner.nextLine());
        return new Transaction(date, time, description, vendor, -amount); //John helped with design choice
    }

    //Read transaction from transaction file
    public static List<Transaction> readAllEntries(String filename){
        //initalize empty list to store transations
        List<Transaction> transactionList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] splitPerEntries = line.split("\\|");
                Transaction transaction = new Transaction(splitPerEntries[0], splitPerEntries[1], splitPerEntries[2], splitPerEntries[3], Double.parseDouble(splitPerEntries[4]));
                transactionList.add(transaction);
            }
        }
        catch (IOException e){
            System.out.println("Problem: " + e.getMessage());
        }
        return transactionList;
    }

    //Ledger displays all entries, so we read from the csv file using buffered reader method
    public static void displayAllEntries(){
        List<Transaction> transactionList = readAllEntries("transactionFolder/transaction.csv");
        System.out.println("Here is all the transaction for your account: ");
        for (int i = 0; i < transactionList.size(); i++){
            Transaction consoleDisplayAllEntries = transactionList.get(i);
            System.out.println(consoleDisplayAllEntries.displayTransactionFormat());
        }
    }

    //Method that display only deposit transaction to console
    public static void displayDepositEntries(){
        List<Transaction> transactionList = readAllEntries("transactionFolder/transaction.csv");
        System.out.println("Here is all the Deposit transaction entries for your account: ");
        for (int i = 0; i < transactionList.size(); i++){
            if (transactionList.get(i).getAmount() > 0){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }
    }

    //Display only debits entries
    public static void displayDebitPaymentEntries(){
        List<Transaction> transactionList = readAllEntries("transactionFolder/transaction.csv");
        System.out.println("Here is all the Debit transaction entries for your account: ");
        for (int i = 0; i < transactionList.size(); i++){
            if (transactionList.get(i).getAmount() < 0){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }
    }

    //Custom search to filter from month to date
    public static void monthToDateOnlyEntries(){
        List<Transaction> transactionList = readAllEntries("transactionFolder/transaction.csv");
        for (int i = 0; i < transactionList.size(); i++){
            if (transactionList.get(i).getDate().getMonth() == LocalDate.now().getMonth() && transactionList.get(i).getDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }
    }

    //Previous month filter
    public static void previousMonthEntries() {
        List<Transaction> transactionList = readAllEntries("transactionFolder/transaction.csv");
        for (int i = 0; i < transactionList.size(); i++) {
            if (transactionList.get(i).getDate().getMonth() == LocalDate.now().getMonth().minus(1) && transactionList.get(i).getDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }
    }

    //year to date filter
    public static void yearToDateOnlyEntries(){
        
    }

    //Custom search by vendor name
    //Use .equals or if a line contains this user input print it out
    public static void searchByVendor(Scanner scanner){
        String userVendorName = scanner.nextLine();
        List<Transaction> transactionList = readAllEntries("transactionFolder/transaction.csv");
        System.out.println("Here is all the transaction entries for" + userVendorName + " : ");
        for (int i = 0; i < transactionList.size(); i++){
            if (transactionList.get(i).getVendor().contains(userVendorName)){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }

    }

}
