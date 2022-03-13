package com.example.bankaccountbalancerestapi.entity;


import com.example.bankaccountbalancerestapi.Currency;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Transaction extends Account{

    @CsvBindByName
    LocalDateTime operationTime;
    @CsvBindByName
    String beneficiary;
    @CsvBindByName
    String comment;
    @CsvBindByName
    double amount;
    @CsvBindByName
    Currency transCurrency;

    public Transaction(UUID accountNumber, String owner, double balance, Currency currency, LocalDateTime operationTime, String beneficiary, String comment, double amount, Currency transCurrency) {
        super(accountNumber, owner, balance, currency);
        this.operationTime = operationTime;
        this.beneficiary = beneficiary;
        this.comment = comment;
        this.amount = amount;
        this.transCurrency = transCurrency;
    }


    @Override
    public String toString() {
        return  accountNumber + ", " + owner + ", " +  balance + ", " +  currency + ", " + operationTime + ", " + beneficiary + ", " + comment + ", " + amount + ", " + currency;
    }
}
