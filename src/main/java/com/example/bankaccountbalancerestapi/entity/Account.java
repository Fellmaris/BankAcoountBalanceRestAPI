package com.example.bankaccountbalancerestapi.entity;

import com.example.bankaccountbalancerestapi.Currency;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter

public class Account {

    private UUID accountNumber;
    private String owner;
    private double balance;
    private Currency currency;
    private List<Transaction> transactions;

    public Account(UUID accountNumber, String owner, double balance, Currency currency, List<Transaction> transactions) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.currency = currency;
        this.transactions = transactions;
    }

    public Account(String owner, double balance, Currency currency, List<Transaction> transactions) {
        this.accountNumber = UUID.randomUUID();
        this.owner = owner;
        this.balance = balance;
        this.currency = currency;
        this.transactions = transactions;
    }
}
