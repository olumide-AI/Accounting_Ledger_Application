package accountingledgerapplication;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        /*
                • Home Screen
    o The home screen should give the user the following options. The
    application should continue to run until the user chooses to exit.
    § D) Add Deposit - prompt user for the deposit information and
    save it to the csv file
    § P) Make Payment (Debit) - prompt user for the debit
    information and save it to the csv file
    § L) Ledger - display the ledger screen
    § X) Exit - exit the application
         */
        //Create a main Home screen

        while(true){
            System.out.println("Welcome to the Home screen");
            System.out.println("Please select the following options");
            System.out.println("D - Add deposit information");
            System.out.println("P - Make debit payment");
            System.out.println("L - Display ledger screen");
            System.out.println("E - Exit the application");
            String userInputHomeScreen = scanner.nextLine().toUpperCase().trim();
            switch(userInputHomeScreen) {
                case "D":
                    ;//Add deposit method
                    break;
                case "P":
                    ;//Make payment method
                    break;
                case "L":
                    ;//Display ledger
                    break;
                case "X":
                    System.out.println("Thank you, goodbye");
                    ;//Exit
                    break;
                default:
                    System.out.println("Please enter option D,P,L or X");
            }
        }
    }
    public static void ledger(Scanner scanner){
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
        while (true){
            System.out.println("Display all entries");
            System.out.println("Please select the following options");
            System.out.println("A - Display all entries");
            System.out.println("D - Display deposit information");
            System.out.println("P - Display debit payment");
            System.out.println("R - Display custom report screen");
            System.out.println("H - Go back to the Home screen");
            String userInputLedger = scanner.nextLine().toUpperCase().trim();
            switch (userInputLedger){
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
    //Reports 
}
