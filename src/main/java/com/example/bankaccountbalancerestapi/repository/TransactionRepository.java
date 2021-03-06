package com.example.bankaccountbalancerestapi.repository;

import com.example.bankaccountbalancerestapi.Currency;
import com.example.bankaccountbalancerestapi.entity.Transaction;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();
    private final String path = "accounts.csv";

    public List<Transaction> getTransactions(List<UUID> ids) {
        List<Transaction> transactionsToGet = new ArrayList<>();
        for (UUID uuid: ids) {
            for (Transaction transaction:transactions) {
                if (uuid.equals(transaction.getAccountNumber())){
                    transactionsToGet.add(transaction);
                }
            }
        }
        return transactionsToGet;
    }

    public TransactionRepository() {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            //Only to create default transactions (Delete after first run)
//            LocalDateTime time = LocalDateTime.now();
//            transactions.add(new Transaction(UUID.fromString("7f04c6cc-b08c-4e9e-8ce3-a3a30bc9ff4b"), "Arturas", 20.22123, Currency.EUR, time, "Tadas", "Pleasure doing buisness with you", 13.05123, Currency.EUR));
//            transactions.add(new Transaction(UUID.fromString("7f04c6cc-b08c-4e9e-8ce3-a3a30bc9ff4c"), "Dainius", 200.22123, Currency.GBP, time, "Pranas", "Pleasure doing buisness with you 2", 13.27123, Currency.GBP));

            CSVReader reader = new CSVReader(new FileReader(file));
            List<String[]> transactionsFromFile = reader.readAll();
            transactionsFromFile.forEach(i -> {
                transactions.add(new Transaction(UUID.fromString(i[0]), i[1], Double.parseDouble(i[2]), Currency.valueOf(i[3].trim()), LocalDateTime.parse(i[4]), i[5], i[6], Double.parseDouble(i[7]), Currency.valueOf(i[8].trim())));
            });

            closeUp();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void closeUp() {
        try {

            File csvFile = new File(path);
            PrintWriter out = new PrintWriter(csvFile);
            transactions.forEach(i -> {out.printf("%s,%s,%f,%s,%s,%s,%s,%f,%s\n", i.getAccountNumber(), i.getOwner(), i.getBalance(), i.getCurrency(), i.getOperationTime(), i.getBeneficiary(), i.getComment(), i.getAmount(), i.getTransCurrency());});
            out.close();


        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Transaction> getTrasactionsFromTo(UUID id, LocalDateTime from, LocalDateTime to) {
        return transactions.stream().filter(i -> {
            if(i.getAccountNumber().equals(id) && i.getOperationTime().isAfter(from) && i.getOperationTime().isBefore(to)){
                return true;
            } else {
                return false;
            }
        }).collect(Collectors.toList());
    }

    public void createTransactions (String transactionList) {
        String transactionArray [] = transactionList.replaceAll("\r\n", ",").split(",", 0);

        int index = -1;
        for (int i = 0; i <= transactionArray.length-1; i += 9) {
            Transaction transaction = new Transaction(UUID.fromString(transactionArray[index + 1].trim()), transactionArray[index + 2].trim(), Double.parseDouble(transactionArray[index + 3].trim()), Currency.valueOf(transactionArray[index + 4].trim()), LocalDateTime.parse(transactionArray[index + 5].trim()), transactionArray[index + 6].trim(), transactionArray[index + 7].trim(), Double.parseDouble(transactionArray[index + 8].trim()), Currency.valueOf(transactionArray[index + 9].trim()));
            index += 9;
            transactions.add(transaction);
        }
    closeUp();
    }
}
