package com.example.bankaccountbalancerestapi.controller;

import static com.example.bankaccountbalancerestapi.ApiPath.*;

import com.example.bankaccountbalancerestapi.entity.Transaction;
import com.example.bankaccountbalancerestapi.resourceToCsv.ResourceToCSV;
import com.example.bankaccountbalancerestapi.service.TransactionService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.css.CSSValueList;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@Api (tags = "Bank transactions controller")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController (TransactionService transactionService) {this.transactionService = transactionService;}

    @ApiOperation(value = "Get all transactions for account", tags = "getTransactions", httpMethod = "GET")
    @GetMapping (produces = "text/csv", value = "/transactions/{ids}")
    public void getTransactions(@PathVariable(ID_VARIABLE) List<UUID> id, HttpServletResponse response){
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
}
