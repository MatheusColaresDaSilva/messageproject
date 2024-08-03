package com.smbackoffice.service;

import com.smbackoffice.entity.Account;
import com.smbackoffice.entity.Message;
import com.smbackoffice.entity.Transaction;
import com.smbackoffice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction newTransaction(Account account, Message message) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setMessage(message);
        return transactionRepository.save(transaction);
    }
}
