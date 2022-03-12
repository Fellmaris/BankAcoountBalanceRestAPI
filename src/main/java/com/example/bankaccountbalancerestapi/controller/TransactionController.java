package com.example.bankaccountbalancerestapi.controller;

import static com.example.bankaccountbalancerestapi.ApiPath.*;

import com.example.bankaccountbalancerestapi.entity.Transaction;
import com.example.bankaccountbalancerestapi.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@Api (tags = "Bank transactions controller")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController (TransactionService transactionService) {this.transactionService = transactionService;}

    @ApiOperation(value = "Get all transactions for account", tags = "getTransactions", httpMethod = "GET")
    @GetMapping (produces = MediaType.APPLICATION_JSON_VALUE, value = "/transactions/{id}")
    public List<Transaction> getTransactionsForAccount(@PathVariable(ID_VARIABLE) UUID id){return transactionService.getAllTransactionsForAccount(id);}

    @ApiOperation(value = "Add new transaction", tags = "addTransaction", httpMethod = "POST")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/transactions")
    public void addTransactions (@RequestBody Transaction transaction){transactionService.createTransaction(transaction);}
}
