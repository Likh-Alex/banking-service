package com.dev.bankingservice.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private BigDecimal balance;
    @Column(name = "active")
    private boolean isActive;
    @ManyToOne
    private User user;

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ", accountNumber='" + accountNumber
                + ", currency=" + currency
                + ", balance=" + balance
                + ", isActive=" + isActive
                + '}';
    }
}
