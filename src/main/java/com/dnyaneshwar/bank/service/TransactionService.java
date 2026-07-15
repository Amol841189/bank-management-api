package com.dnyaneshwar.bank.service;

import com.dnyaneshwar.bank.dto.TransactionResponse;
import com.dnyaneshwar.bank.entity.Transaction;
import com.dnyaneshwar.bank.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    // Constructor Injection ( Recommended )
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    
    public List<TransactionResponse> getTransactionHistory(String accountNumber) {

        List<Transaction> transactions =
            transactionRepository.findByAccountAccountNumberOrderByCreatedAtDesc(accountNumber);

        return transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getId(),
                        transaction.getType().toString(),
                        transaction.getAmount(),
                        transaction.getBalanceAfterTransaction(),
                        transaction.getDescription(),
                        transaction.getCreatedAt()
                ))
                .toList();
    }
}
