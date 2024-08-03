package com.smbackoffice.repository;

import com.smbackoffice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByAccountIdAndMessageId(Long accountId, Long messageId);

}
