package com.dev.bankingservice.service;

import com.dev.bankingservice.entity.Account;
import java.util.List;

public interface AccountService {
    Account save(Account account);

    List<Account> getAllByPhoneNumber(String phoneNumber);

    Account getByAccountNumber(String accountNumber);

    void block(String accountNumber);
}
