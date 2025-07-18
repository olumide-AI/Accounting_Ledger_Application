package accountingledgerapplication.repository;

import accountingledgerapplication.model.Transaction;
import accountingledgerapplication.service.ReceiptWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "transactionFolder/transaction.csv";

    // Write a single transaction to the CSV file AND also generate a .txt receipt
    public static void writeTransactionsToFile(Transaction transactionEntry) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)) {
            fileWriter.write(transactionEntry.displayTransactionFormat() + "\n");

            // 🧾 Also write a human-readable receipt in receipts/
            ReceiptWriter.writeReceipt(List.of(transactionEntry));

        } catch (IOException e) {
            System.out.println("File writing transaction error: " + e.getMessage());
        }
    }

    // Read and return all transactions sorted newest to oldest
    public static List<Transaction> readAllTransactions() {
        List<Transaction> transactionList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitPerEntries = line.split("\\|");
                Transaction transaction = new Transaction(
                        splitPerEntries[0],
                        splitPerEntries[1],
                        splitPerEntries[2],
                        splitPerEntries[3],
                        Double.parseDouble(splitPerEntries[4])
                );
                transactionList.add(transaction);
            }
        } catch (IOException e) {
            System.out.println("File Reading transaction error: " + e.getMessage());
        }

        // Sort transactions from newest to oldest
        for (int i = 0; i < transactionList.size() - 1; i++) {
            for (int j = i + 1; j < transactionList.size(); j++) {
                Transaction first = transactionList.get(i);
                Transaction second = transactionList.get(j);
                boolean swap = false;

                if (first.getDate().isBefore(second.getDate())) {
                    swap = true;
                } else if (first.getDate().isEqual(second.getDate()) &&
                        first.getTime().isBefore(second.getTime())) {
                    swap = true;
                }

                if (swap) {
                    transactionList.set(i, second);
                    transactionList.set(j, first);
                }
            }
        }

        return transactionList;
    }
}
