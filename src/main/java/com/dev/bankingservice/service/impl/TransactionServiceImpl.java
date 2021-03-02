package com.dev.bankingservice.service.impl;

import com.dev.bankingservice.entity.Account;
import com.dev.bankingservice.entity.Transaction;
import com.dev.bankingservice.repository.AccountRepository;
import com.dev.bankingservice.repository.TransactionRepository;
import com.dev.bankingservice.service.TransactionService;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<Transaction> getAllByAccount(int page, int size, Account account) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("date").descending());
        return transactionRepository.getAllByAccount(account.getAccountNumber(), pageable);
    }

    @Override
    public void transferFunds(String accountFrom, String accountTo, Double amount) {
        Account withdrawAccount = accountRepository
                .getByAccountNumber(accountFrom).orElseThrow(()
                        -> new EntityNotFoundException("Can not find account with number : "
                        + accountFrom));
        Account inputAccount = accountRepository
                .getByAccountNumber(accountTo).orElseThrow(()
                        -> new EntityNotFoundException("Can not find account with number : "
                        + accountTo));
        Transaction withdrawTransaction = new Transaction();
        withdrawTransaction.setDate(LocalDateTime.now());
        withdrawTransaction.setType(Transaction.TransactionType.OUTCOMING);
        withdrawTransaction.setAccountFrom(withdrawAccount);
        withdrawTransaction.setAccountTo(inputAccount);
        withdrawTransaction.setAmount(amount);
        transactionRepository.save(withdrawTransaction);
        if (withdrawAccount.getBalance() - amount <= 0.0) {
            throw new RuntimeException("Not enough funds on account : " + accountFrom);
        }
        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);
        accountRepository.save(withdrawAccount);
        inputAccount.setBalance(inputAccount.getBalance() + amount);
        accountRepository.save(inputAccount);
        Transaction inputTransaction = new Transaction();
        inputTransaction.setDate(LocalDateTime.now());
        inputTransaction.setType(Transaction.TransactionType.INCOMING);
        inputTransaction.setAccountFrom(withdrawAccount);
        inputTransaction.setAccountTo(inputAccount);
        inputTransaction.setAmount(amount);
        transactionRepository.save(inputTransaction);
    }
}
