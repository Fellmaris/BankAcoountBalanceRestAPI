package com.example.bankaccountbalancerestapi.logic;

import com.example.bankaccountbalancerestapi.entity.Transaction;

import java.util.List;

public class BalanceCounter {
    public double countBalance (List<Transaction> transactions){
        double endBalance = transactions.get(0).getBalance();
        for (Transaction transaction: transactions) {
            endBalance -= transaction.getAmount();
        }
        return endBalance;
    }
}
