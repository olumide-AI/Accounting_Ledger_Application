package accountingledgerapplication.ui;

import accountingledgerapplication.model.Transaction;
import accountingledgerapplication.repository.FileHandler;

import java.util.Scanner;

import static accountingledgerapplication.app.Main.*;

public class AppHomeScreen {

    static Scanner scanner = new Scanner(System.in);

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
                    System.out.println(TextStyler.colorText("⚠\uFE0F Please enter option D, P, L, or E ⚠\uFE0F\n", TextStyler.RED));
            }
        }

        scanner.close(); // This closes System.in, so don't reopen it elsewhere
    }
}
