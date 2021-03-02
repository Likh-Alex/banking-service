package com.dev.bankingservice.controller;

import com.dev.bankingservice.entity.Account;
import com.dev.bankingservice.entity.Currency;
import com.dev.bankingservice.entity.Role;
import com.dev.bankingservice.entity.Transaction;
import com.dev.bankingservice.entity.User;
import com.dev.bankingservice.service.AccountService;
import com.dev.bankingservice.service.RoleService;
import com.dev.bankingservice.service.TransactionService;
import com.dev.bankingservice.service.UserService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class InjectData {
    private final UserService userService;
    private final RoleService roleService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @PostConstruct
    void inject() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.save(adminRole);
        roleService.save(userRole);

        Role manager1 = roleService.getByRoleName("ADMIN");
        Role customer1 = roleService.getByRoleName("USER");
        System.out.println(manager1.toString() + "\n" + customer1.toString());

        User senderUser = new User();
        senderUser.setName("Gregory");
        senderUser.setDateOfBirth(LocalDate.of(1991, 05, 12));
        senderUser.setPassword("1234");
        senderUser.setRoles(Set.of(userRole));
        senderUser.setPhoneNumber("54321");
        User senderFromDb = userService.save(senderUser);
        System.out.println(senderFromDb.toString());
        senderUser.setName("Kvaker");
        userService.save(senderUser);
        System.out.println(userService.getByPhoneNumber(senderUser.getPhoneNumber()).toString());
        User readUserFromDb = userService.getById(senderUser.getId());
        System.out.println(readUserFromDb.getPassword().equals(senderUser.getPassword()));

        Account senderAccount = new Account();
        senderAccount.setUser(senderFromDb);
        senderAccount.setAccountNumber("1234");
        senderAccount.setBalance(new BigDecimal(0));
        senderAccount.setActive(true);
        senderAccount.setCurrency(Currency.UAH);
        Account senderAccountFromDb = accountService.save(senderAccount);
        System.out.println(senderAccountFromDb);
        List<Account> allByPhoneNumber = accountService.getAllByPhoneNumber("54321");
        System.out.println(allByPhoneNumber);
        accountService.block("1234");
        System.out.println(accountService.getByAccountNumber("1234").toString());

        User receiverUser = new User();
        receiverUser.setName("Mike");
        receiverUser.setPassword("1234");
        receiverUser.setRoles(Set.of(userRole));
        receiverUser.setPhoneNumber("654321");
        receiverUser.setDateOfBirth(LocalDate.of(2003, 8, 3));
        User receiverFromDb = userService.save(receiverUser);

        Account receiverAccount = new Account();
        receiverAccount.setUser(receiverFromDb);
        receiverAccount.setAccountNumber("0987");
        receiverAccount.setBalance(new BigDecimal("100.0"));
        receiverAccount.setActive(true);
        receiverAccount.setCurrency(Currency.UAH);
        Account savedReceiverAccount = accountService.save(receiverAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("10.0"));
        transaction.setDate(LocalDateTime.now());
        transaction.setAccountFrom(senderAccountFromDb);
        transaction.setAccountTo(savedReceiverAccount);
        transaction.setType(Transaction.TransactionType.OUTCOMING);
        transactionService.transferFunds(senderAccountFromDb.getAccountNumber(),
                savedReceiverAccount.getAccountNumber(),
                new BigDecimal("10.0"));
        List<Transaction> allByAccount = transactionService
                .getAllByAccount(1, 1, senderAccountFromDb);
        System.out.println(allByAccount);
    }
}
