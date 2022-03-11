package com.example.bankaccountbalancerestapi.entity;

import com.example.bankaccountbalancerestapi.Currency;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class Account {

    @CsvBindByName
    protected UUID accountNumber;
    @CsvBindByName
    protected String owner;
    @CsvBindByName
    protected double balance;
    @CsvBindByName
    protected Currency currency;
    @CsvBindByName
    protected Transaction transaction;

    public Account(UUID accountNumber, String owner, double balance, Currency currency) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
        this.currency = currency;
    }

    public Account(String owner, double balance, Currency currency) {
        this.accountNumber = UUID.randomUUID();
        this.owner = owner;
        this.balance = balance;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return  accountNumber + ", " + owner + ", " +  balance + ", " +  currency + ", " + transaction;
    }
}
