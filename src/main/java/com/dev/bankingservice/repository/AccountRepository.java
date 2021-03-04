package com.dev.bankingservice.repository;

import com.dev.bankingservice.entity.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("FROM Account a JOIN FETCH a.user u WHERE u.phoneNumber = ?1")
    List<Account> getAllByPhoneNumber(String phoneNumber);

    Optional<Account> getByAccountNumber(String accountNumber);
}
