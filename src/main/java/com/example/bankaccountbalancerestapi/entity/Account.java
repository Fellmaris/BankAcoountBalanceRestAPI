package com.example.bankaccountbalancerestapi.entity;

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
}
