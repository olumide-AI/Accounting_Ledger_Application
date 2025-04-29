package accountingledgerapplication;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private static final String FILE_PATH = "transactionFolder/transaction.csv";

    //Write the transaction to the csv file (Copied same code from main
    //Write the user deposit information to file
    public static void writeTransactionsToFile(Transaction transactionEntry) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)) {
            fileWriter.write(transactionEntry.displayTransactionFormat() + "\n");
        } catch (IOException e) {
            System.out.println("File writing transaction error: " + e.getMessage());
        }
    }
}
