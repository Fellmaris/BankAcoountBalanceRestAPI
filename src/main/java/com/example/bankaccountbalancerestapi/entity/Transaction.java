package com.example.bankaccountbalancerestapi.entity;

import com.example.bankaccountbalancerestapi.Currency;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Transaction extends Account{

    LocalDateTime operationTime;
    String beneficiary;
    String comment;
    double amount;
    Currency currency;

    public Transaction(String owner, double balance, Currency currency, List<Transaction> transactions, LocalDateTime operationTime, String beneficiary, String comment, double amount, Currency currency1) {
        super(owner, balance, currency, transactions);
        this.operationTime = operationTime;
        this.beneficiary = beneficiary;
        this.comment = comment;
        this.amount = amount;
        this.currency = currency1;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "\noperationTime=" + operationTime +
                "\n, beneficiary='" + beneficiary +
                "\n, comment='" + comment +
                "\n, amount=" + amount +
                "\n, currency=" + currency +
                "\n}";
    }
}
