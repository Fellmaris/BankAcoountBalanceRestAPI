package com.example.bankaccountbalancerestapi.logic;

import com.example.bankaccountbalancerestapi.entity.Transaction;

import java.time.LocalDateTime;
import java.util.Comparator;

public class TransactionDateComparer implements Comparator<Transaction> {
    @Override
    public int compare(Transaction x, Transaction y) {
        int startComparison = compare(x.getOperationTime(), y.getOperationTime());
        return startComparison;

    }
    private static int compare(LocalDateTime a, LocalDateTime b) {
        return a.isBefore(b) ? -1
                : a.isAfter(b) ? 1
                : 0;
    }
}
