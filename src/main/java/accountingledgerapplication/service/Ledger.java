package accountingledgerapplication.service;

import accountingledgerapplication.model.Transaction;
import accountingledgerapplication.repository.FileHandler;

import java.util.List;

public class Ledger {

    //Ledger displays all entries, so we read from the csv file using buffered reader method
    public static void displayAllEntries(){
        List<Transaction> transactionList = FileHandler.readAllTransactions();
        System.out.println("Here is all the transaction for your account: ");
        for (Transaction transaction : transactionList) {
            System.out.println(transaction.displayTransactionFormat() + "\n");
        }
    }

    //Method that display only deposit transaction to console
    public static void displayDepositEntries(){
        List<Transaction> transactionList = FileHandler.readAllTransactions();
        System.out.println("Here is all the Deposit transaction entries for your account: ");
        for (Transaction transaction : transactionList) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction.displayTransactionFormat() + "\n");
            }
        }
    }

    //Display only debits entries
    public static void displayDebitPaymentEntries(){
        List<Transaction> transactionList = FileHandler.readAllTransactions();
        System.out.println("Here is all the Debit transaction entries for your account: ");
        for (Transaction transaction : transactionList) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction.displayTransactionFormat() + "\n");
            }
        }
    }
}
