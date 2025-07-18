package accountingledgerapplication.ui;

import accountingledgerapplication.model.Transaction;
import accountingledgerapplication.repository.FileHandler;

import java.util.Scanner;

import static accountingledgerapplication.app.Main.*;

public class AppHomeScreen {

    static Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean flag = true;
        while (flag) {
            System.out.println("Welcome to the Home Screen");
            System.out.println("\nPlease select the following options:");
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
                    ledgerHomeScreen(); // Consider moving this to its own class too
                    break;
                case "E":
                    System.out.println("Thank you, Goodbye");
                    flag = false;
                    break;
                default:
                    System.out.println("Please enter option D, P, L, or E\n");
            }
        }

        scanner.close(); // This closes System.in, so don't reopen it elsewhere
    }
}
