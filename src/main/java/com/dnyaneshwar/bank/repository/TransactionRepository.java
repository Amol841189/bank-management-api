package com.dnyaneshwar.bank.repository;

import com.dnyaneshwar.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountAccountNumberOrderByCreatedAtDesc(String accountNumber);
}