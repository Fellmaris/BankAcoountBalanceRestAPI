package com.example.bankaccountbalancerestapi.service;

import com.example.bankaccountbalancerestapi.entity.Transaction;
import com.example.bankaccountbalancerestapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions(List<UUID> id) {
        return transactionRepository.getTransactions(id);
    }

    public void createTransactions (String transactions){
        transactionRepository.createTransactions(transactions);
    }

    public List<Transaction> getTransactionsFromTo (UUID id, LocalDateTime from, LocalDateTime to){
        return transactionRepository.getTrasactionsFromTo(id, from, to);
    }

}
