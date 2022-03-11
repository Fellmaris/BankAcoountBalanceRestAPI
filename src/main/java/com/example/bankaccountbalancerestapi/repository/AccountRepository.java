package com.example.bankaccountbalancerestapi.repository;

import com.example.bankaccountbalancerestapi.Currency;
import com.example.bankaccountbalancerestapi.entity.Account;
import com.example.bankaccountbalancerestapi.entity.Transaction;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
public class AccountRepository {
    private List<Account> accounts =  new ArrayList<>();
    private final String path = "accounts.csv";

    public AccountRepository() {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            CSVReader csvReader = new CSVReader(Files.newBufferedReader(Paths.get(path)));
            List<Account> accounts = new ArrayList<>();
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                MappingIterator<Transaction> transactionIterrator = new CsvMapper().readerWithSchemaFor(Transaction.class).readValues(nextRecord[4]);
                Account account = new Account(UUID.fromString(nextRecord[0]), nextRecord[1], Double.parseDouble(nextRecord[2]), Currency.valueOf(nextRecord[3]), transactionIterrator.readAll());
                accounts.add(account);
            }
            this.accounts = accounts;

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void closeUp() {
        try {
            LocalDateTime time = LocalDateTime.now();
            accounts.add(new Account("Arturas", 20.22, Currency.EUR, Arrays.asList(new Transaction("Arturas", 20.22, Currency.EUR, new ArrayList<Transaction>(),  time, "Tadas", "Pleasure doing buisness with you", 13.05, Currency.EUR))));
            File csvFile = new File(path);
            PrintWriter out = new PrintWriter(csvFile);
            accounts.forEach(i -> {out.printf("%s, %s, %f, %s, %s\n", i.getAccountNumber(), i.getOwner(), i.getBalance(), i.getCurrency(), i.getTransactions());});
            out.close();


        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
