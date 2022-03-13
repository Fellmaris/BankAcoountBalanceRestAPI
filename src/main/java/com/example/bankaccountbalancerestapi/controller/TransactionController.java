package com.example.bankaccountbalancerestapi.controller;

import static com.example.bankaccountbalancerestapi.ApiPath.*;

import com.example.bankaccountbalancerestapi.logic.BalanceCounter;
import com.example.bankaccountbalancerestapi.logic.TransactionDateComparer;
import com.example.bankaccountbalancerestapi.entity.Transaction;
import com.example.bankaccountbalancerestapi.logic.ResourceToCSV;
import com.example.bankaccountbalancerestapi.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.util.*;


@RestController
@RequestMapping
@Api (tags = "Bank transactions controller")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController (TransactionService transactionService) {this.transactionService = transactionService;}

    @ApiOperation(value = "Get all transactions for account", tags = "getTransactions", httpMethod = "GET")
    @GetMapping (produces = "text/csv", value = "/transactions/{ids}")
    public void getTransactions(@PathVariable(IDS_VARIABLE) List<UUID> id, HttpServletResponse response){
        try {
            PrintWriter out = response.getWriter();
            for (Transaction transaction : transactionService.getTransactions(id)) {
                out.println(transaction.toString());
            }
            out.flush();
            response.flushBuffer();
            out.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }

    @ApiOperation(value = "Add new transaction", tags = "addTransaction", httpMethod = "POST")
    @PostMapping(value = "/transactions")
    public void addTransactions (@RequestBody Resource resource){
        transactionService.createTransactions(ResourceToCSV.asString(resource));
    }

    @ApiOperation(value = "Get transactions for account from to", tags = "getTransactionsByDate", httpMethod = "GET")
    @GetMapping (produces = "text/csv", value = "/transactions/{id}/{dateFrom}/{dateTo}")
    public void getTransactionsByDate (@PathVariable(ID_VARIABLE) UUID id, @PathVariable(DATE_FROM_VARIABLE) String from, @PathVariable(DATE_TO_VARIABLE) String to, HttpServletResponse response){
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        List <Transaction> unsortedTransactions = transactionService.getTransactionsFromTo(id, fromDate.atStartOfDay(), toDate.atTime(23,59,59));
        TransactionDateComparer comparer = new TransactionDateComparer();
        Collections.sort(unsortedTransactions, comparer);
        BalanceCounter counter = new BalanceCounter();

        try {
            PrintWriter out = response.getWriter();
            for (Transaction transaction : unsortedTransactions) {
                out.println(transaction.toString());
            }
            out.println("Date from: " + from + ", date to: " + to + ", end balance: " + counter.countBalance(unsortedTransactions));
            out.flush();
            response.flushBuffer();
            out.close();
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
