package com.example.bankaccountbalancerestapi.service;

import com.example.bankaccountbalancerestapi.entity.Transaction;
import com.example.bankaccountbalancerestapi.repository.TransactionRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactionsForAccount (UUID id) {
        return transactionRepository.getTransactions().stream().filter(i -> i.getAccountNumber().equals(id)).collect(Collectors.toList());
    }

    public void createTransaction (Transaction transaction){
        transactionRepository.createTransaction(transaction);
    }

    public List<Transaction> getTransactionsFromTo (UUID id, LocalDateTime from, LocalDateTime to){
        return transactionRepository.getTrasactionsFromTo(id, from, to);
    }

}
