package com.example.bankaccountbalancerestapi.entity;

import com.example.bankaccountbalancerestapi.Currency;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class Account {

    private UUID accountNumber;
    private String owner;
    private double balance;
    private Currency currency;

    public Account(String owner, double balance, Currency currency) {
        this.accountNumber = UUID.randomUUID();
        this.owner = owner;
        this.balance = balance;
        this.currency = currency;
    }
}
