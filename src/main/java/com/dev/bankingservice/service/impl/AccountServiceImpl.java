package com.dev.bankingservice.service.impl;

import com.dev.bankingservice.entity.Account;
import com.dev.bankingservice.repository.AccountRepository;
import com.dev.bankingservice.service.AccountService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllByPhoneNumber(String phoneNumber) {
        return accountRepository.getAllByPhoneNumber(phoneNumber);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) {
        return accountRepository.getByAccountNumber(accountNumber).orElseThrow(()
                -> new EntityNotFoundException("Can not find account by number: " + accountNumber));
    }

    @Override
    public void block(String accountNumber) {
        Account account = getByAccountNumber(accountNumber);
        account.setActive(false);
        accountRepository.save(account);
    }
}
