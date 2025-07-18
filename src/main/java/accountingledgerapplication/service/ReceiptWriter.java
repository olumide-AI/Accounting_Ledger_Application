package accountingledgerapplication.service;

import accountingledgerapplication.model.Transaction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReceiptWriter {

    public static void writeReceipt(List<Transaction> transactions) {
        // Format filename: receipt-2025-07-18_12-00-00.txt
        DateTimeFormatter fileNameFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String fileTimestamp = LocalDateTime.now().format(fileNameFormat);
        String fileName = "receipts/receipt-" + fileTimestamp + ".txt";

        // Ensure receipts folder exists
        File receiptsDir = new File("receipts");
        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs();
        }

        // Create the receipt content
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("======== RECEIPT ========\n");
            writer.write("Generated: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n\n");

            double total = 0;

            for (Transaction tx : transactions) {
                writer.write(formatTransaction(tx) + "\n");
                total += tx.getAmount(); // using double, since your model uses double not BigDecimal
            }

            writer.write("\nTotal: $" + String.format("%.2f", total));
            writer.write("\n=========================\n");

            System.out.println("Receipt saved to: " + fileName);

        } catch (IOException e) {
            System.err.println("Error writing receipt: " + e.getMessage());
        }
    }

    private static String formatTransaction(Transaction tx) {
        return String.format(
                "%s %s | %s | %s | $%.2f",
                tx.getDate(),
                tx.getTime(),
                tx.getDescription(),
                tx.getVendor(),
                tx.getAmount()
        );
    }
}
