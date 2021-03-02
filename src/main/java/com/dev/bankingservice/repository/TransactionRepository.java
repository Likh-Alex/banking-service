package com.dev.bankingservice.repository;

import com.dev.bankingservice.entity.Transaction;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("FROM Transaction t JOIN FETCH t.accountFrom afo JOIN FETCH t.accountTo ato "
            + "WHERE afo.accountNumber = ?1 OR ato.accountNumber = ?1")
    List<Transaction> getAllByAccount(String accountNumber, PageRequest pageable);
}
