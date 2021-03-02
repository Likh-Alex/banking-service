package com.dev.bankingservice.service;

import com.dev.bankingservice.entity.Account;
import com.dev.bankingservice.entity.Transaction;
import java.util.List;

public interface TransactionService {
    List<Transaction> getAllByAccount(int page, int size, Account account);

    void transferFunds(String accountFrom, String accountTo, Double amount);
}
