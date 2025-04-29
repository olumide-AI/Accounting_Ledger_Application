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
    static final String FILE_PATH = "transactionFolder/transaction.csv";

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
                    Transaction transaction = addDepositInformation( scanner);
                    writeTransactionsToFile(transaction);
                    break;
                case "P":
                    Transaction transaction1 = addDebitPaymentInformation( scanner);
                    writeTransactionsToFile(transaction1);
                    break;
                case "L":
                    ledgerHomeScreen( scanner);
                    break;
                case "E":
                    System.out.println("Thank you, goodbye");
                    ;//Exit
                    flag = false;
                    break;

                default:
                    System.out.println("Please enter option D,P,L or X");
            }
        }
    }

    public static void ledgerHomeScreen(Scanner scanner) {
        boolean flag = true;

        while (flag) {
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
                    displayAllEntries();
                    break;
                case "D":
                    displayDepositEntries();
                    break;
                case "P":
                    displayDebitPaymentEntries();
                    break;
                case "R":
                    reportHomeScreen(scanner);
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
    public static void reportHomeScreen(Scanner scanner) {
        boolean flag = true;
        while (flag) {
            System.out.println("Run your custom search on your report");
            System.out.println("Please select the following options");
            System.out.println("1 - Display Month to Date information"); //yyyy-mm-dd
            System.out.println("2 - Display previous month information"); //
            System.out.println("3 - Display year to date information");
            System.out.println("4 - Display previous year information");
            System.out.println("5 - To search by vendor");
            System.out.println("0 - Go back to the Ledger screen"); //The book says report
            String userInputReport = scanner.nextLine();

            switch (userInputReport) {
                case "1":
                    monthToDateOnlyEntries();
                    break;
                case "2":
                    previousMonthEntries();
                    break;
                case "3":
                    yearToDateOnlyEntries();
                    break;
                case "4":
                    previousYearEntries();
                    break;
                case "5":
                    searchByVendor(scanner);
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
    public static Transaction addDepositInformation(Scanner scanner) {
        System.out.println("What date is your transaction: ");
        String date = scanner.nextLine();
        System.out.println("What time was your transaction: ");
        String time = scanner.nextLine();
        System.out.println("What is your transaction description: ");
        String description = scanner.nextLine();
        System.out.println("What is your transaction vendor name: ");
        String vendor = scanner.nextLine();
        System.out.println("What was the Deposit amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        return new Transaction(date, time, description, vendor, amount);
    }

    //Delete this
    //Write the user deposit information to file
    public static void writeTransactionsToFile(Transaction transactionEntry) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)) {
            fileWriter.write(transactionEntry.displayTransactionFormat() + "\n");
        } catch (IOException e) {
            System.out.println("File writing transaction error: " + e.getMessage());
        }
    }

    //Debit payment information (will include "-" price)
    //We can call the writeTransactionsToFile() method to write it into our csv file
    public static Transaction addDebitPaymentInformation(Scanner scanner) {
        System.out.println("What date is your transaction: ");
        String date = scanner.nextLine();
        System.out.println("What time was your transaction: ");
        String time = scanner.nextLine();
        System.out.println("What is your transaction description: ");
        String description = scanner.nextLine();
        System.out.println("What is your transaction vendor name: ");
        String vendor = scanner.nextLine();
        System.out.println("What was the amount : ");
        double amount = Double.parseDouble(scanner.nextLine());
        return new Transaction(date, time, description, vendor, -1 * amount);
    }

    //Read transaction from transaction file
    public static List<Transaction> readAllEntries(String filename){
        //initialize empty list to store transactions
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
            System.out.println("File Reading transaction error:  " + e.getMessage());
        }
        return transactionList;
    }

    //Ledger displays all entries, so we read from the csv file using buffered reader method
    public static void displayAllEntries(){
        List<Transaction> transactionList = readAllEntries(FILE_PATH);
        System.out.println("Here is all the transaction for your account: ");
        for (Transaction transaction : transactionList) {
            System.out.println(transaction.displayTransactionFormat());
        }
    }

    //Method that display only deposit transaction to console
    public static void displayDepositEntries(){
        List<Transaction> transactionList = readAllEntries(FILE_PATH);
        System.out.println("Here is all the Deposit transaction entries for your account: ");
        for (int i = 0; i < transactionList.size(); i++){
            if (transactionList.get(i).getAmount() > 0){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }
    }

    //Display only debits entries
    public static void displayDebitPaymentEntries(){
        List<Transaction> transactionList = readAllEntries(FILE_PATH);
        System.out.println("Here is all the Debit transaction entries for your account: ");
        for (int i = 0; i < transactionList.size(); i++){
            if (transactionList.get(i).getAmount() < 0){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }
    }

    //Custom search to filter from month to date
    public static void monthToDateOnlyEntries(){
        List<Transaction> transactionList = readAllEntries(FILE_PATH);
        for (Transaction transaction : transactionList) {
            if (transaction.getDate().getMonth() == LocalDate.now().getMonth() && transaction.getDate().getYear() == LocalDate.now().getYear()) {
                System.out.println(transaction.displayTransactionFormat());
            }
        }
    }

    //Previous month filter
    //Edge case if jan 2025 - dec 2024
    public static void previousMonthEntries() {
        List<Transaction> transactionList = readAllEntries(FILE_PATH);
        for (int i = 0; i < transactionList.size(); i++) {
            if (transactionList.get(i).getDate().getMonth() == LocalDate.now().getMonth().minus(1) && transactionList.get(i).getDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }
    }

    //year to date filter
    public static void yearToDateOnlyEntries(){
        List<Transaction> transactionList = readAllEntries(FILE_PATH);
        for (Transaction transaction : transactionList){
            if (transaction.getDate().getYear() == LocalDate.now().getYear()){
                System.out.println(transaction.displayTransactionFormat());
            }
        }

    }

    //Filter for Previous year
    public static void  previousYearEntries(){
        List<Transaction> transactionList = readAllEntries(FILE_PATH);
        for (Transaction transaction : transactionList){
            if (transaction.getDate().getYear() == LocalDate.now().getYear()-1){
                System.out.println(transaction.displayTransactionFormat());
            }
        }
    }

    //Custom search by vendor name
    //Use .equals or if a line contains this user input print it out
    public static void searchByVendor(Scanner scanner){
        System.out.println("What is the vendor name ");
        String userVendorName = scanner.nextLine();
        List<Transaction> transactionList = readAllEntries(FILE_PATH);
        System.out.println("Here is all the transaction entries for" + userVendorName + " : ");
        for (int i = 0; i < transactionList.size(); i++){
            if (transactionList.get(i).getVendor().toLowerCase().contains(userVendorName)){
                System.out.println(transactionList.get(i).displayTransactionFormat());
            }
        }

    }

}
