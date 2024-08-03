package com.smbackoffice.service;

import com.smbackoffice.dto.request.DeductionRequestDTO;
import com.smbackoffice.entity.Account;
import com.smbackoffice.entity.Message;
import com.smbackoffice.exception.AccountNotFoundException;
import com.smbackoffice.repository.AccountRepository;
import com.smbackoffice.entity.Transaction;
import com.smbackoffice.repository.MessageRepository;
import com.smbackoffice.repository.TransactionRepository;
import com.smsender.exception.MessageNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountPreService implements AccountActions {

    private AccountRepository accountRepository;
    private MessageRepository messageRepository;
    private TransactionRepository transactionRepository;

    public AccountPreService(AccountRepository accountRepository, MessageRepository messageRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void deduceValueFromAccount(DeductionRequestDTO withdrawRequestDTO) {
        Account account = accountRepository.findById(withdrawRequestDTO.getIdAccount()).orElseThrow(() -> new AccountNotFoundException());
        Message message = messageRepository.findById(withdrawRequestDTO.getIdMessage()).orElseThrow(() -> new MessageNotFoundException());

        account.deductValue(25L);

        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAccount(account);
        transaction.setMessage(message);
        transaction.setMessageCost(25L);
        transactionRepository.save(transaction);
    }
}
