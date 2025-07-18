package accountingledgerapplication.ui;

import accountingledgerapplication.model.Transaction;
import accountingledgerapplication.repository.FileHandler;
import accountingledgerapplication.service.Ledger;

import java.util.Scanner;

import static accountingledgerapplication.app.Main.*;

public class AppHomeScreen {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        boolean flag = true;
        while (flag) {
            System.out.println(TextStyler.colorText("\uD83C\uDFE0 Welcome to the Home Screen \uD83C\uDFE0", TextStyler.BLUE));
            System.out.println(TextStyler.colorText("\nPlease select the following options:", TextStyler.BLUE));
            System.out.println(TextStyler.colorText(String.format("%-2s %-30s %2s", "\uD83D\uDCB0", "D - Add deposit information", "\uD83D\uDCB0"), TextStyler.YELLOW));
            System.out.println(TextStyler.colorText(String.format("%-2s %-30s %2s", "\uD83D\uDCB8", "P - Make debit payment", "\uD83D\uDCB8"), TextStyler.YELLOW));
            System.out.println(TextStyler.colorText(String.format("%-2s %-30s %2s", "\uD83D\uDCD6", "L - Display ledger screen", "\uD83D\uDCD6"), TextStyler.YELLOW));
            System.out.println(TextStyler.colorText(String.format("%-2s %-30s %2s", "\uD83D\uDEAA", "E - Exit the application", "\uD83D\uDEAA"), TextStyler.YELLOW));
            System.out.println("Welcome to the Home Screen");
            System.out.println("\nPlease select the following options:");
            System.out.println("D - Add deposit information");
            System.out.println("P - Make debit payment");
            System.out.println("L - Display ledger screen");
            System.out.println("B - View current balance");
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
                case "B":
                    Ledger.displayBalance(); // ← Call the balance method
                    break;
                case "E":
                    System.out.println("Thank you, Goodbye");
                    flag = false;
                    break;
                default:
                    System.out.println(TextStyler.colorText("⚠\uFE0F Please enter option D, P, L, or E ⚠\uFE0F\n", TextStyler.RED));
                    System.out.println("Please enter option D, P, L, B, or E\n");
            }
        }

        scanner.close(); // This closes System.in, so don't reopen it elsewhere
    }
}
