package com.smbackoffice.service;

import com.smbackoffice.dto.request.DeductionRequestDTO;
import com.smbackoffice.entity.Account;
import com.smbackoffice.entity.Message;
import com.smbackoffice.entity.Transaction;
import com.smbackoffice.exception.AccountNotFoundException;
import com.smbackoffice.exception.MessageNotFoundException;
import com.smbackoffice.repository.AccountRepository;
import com.smbackoffice.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private TransactionService transactionService;

    public AccountService(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Transactional
    public Optional<Transaction> deduceValueFromAccount(Long idAccount, Message message) {
        Account account = accountRepository.findById(idAccount).orElseThrow(() -> new AccountNotFoundException());

        AccountDeductionStrategy accountDeductionStrategy = AccountDeductionFactory.accountDeductionDiscover(account);
        if(!message.getIsWhats()) {
            accountDeductionStrategy.deductValue(0.25, account);
        } else {
            accountDeductionStrategy.deductValue(0.15, account);
        }

        return Optional.ofNullable(transactionService.newTransaction(account, message));
    }
}
