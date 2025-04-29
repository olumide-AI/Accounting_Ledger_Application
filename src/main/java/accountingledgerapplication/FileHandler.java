package accountingledgerapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    //Read transaction from transaction file
    public static List<Transaction> readAllTransactions(){
        //Create an empty list that will eventually store all transaction
        //read from the file
        List<Transaction> transactionList = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH))){
            //Variable that holds each line has we read from file
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] splitPerEntries = line.split("\\|");
                //Create a new transaction object with pieces from the line by
                //splitting them by the pipes
                Transaction transaction = new Transaction(splitPerEntries[0], splitPerEntries[1], splitPerEntries[2], splitPerEntries[3], Double.parseDouble(splitPerEntries[4]));
                //Add this new Transaction to the transactionsList we are building
                transactionList.add(transaction);
            }
        }
        catch (IOException e){
            System.out.println("File Reading transaction error:  " + e.getMessage());
        }
        //Return the fill list of transactions we've built
        return transactionList;
    }
}
