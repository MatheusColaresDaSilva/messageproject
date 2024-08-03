package com.smbackoffice.service;

import com.smbackoffice.dto.request.DeductionRequestDTO;
import com.smbackoffice.entity.Message;
import com.smbackoffice.entity.Transaction;
import com.smbackoffice.enums.StatusType;
import com.smbackoffice.exception.MessageNotFoundException;
import com.smbackoffice.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RequestDeductionAcconuntClientService {

    private static final Logger logger = LoggerFactory.getLogger(RequestDeductionAcconuntClientService.class);

    private MessageRepository messageRepository;
    private AccountService accountService;

    public RequestDeductionAcconuntClientService(MessageRepository messageRepository, AccountService accountService) {
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    @Transactional
    public void deductionAccount(DeductionRequestDTO deductionRequestDTO) {
        Message message = messageRepository.findById(deductionRequestDTO.getIdMessage()).orElseThrow(() -> new MessageNotFoundException());
        try {
            Optional<Transaction> transactionOp = accountService.deduceValueFromAccount(deductionRequestDTO.getIdAccount(), message);
            transactionOp.ifPresent(transaction -> {
                message.setStatus(StatusType.S);
            });

        } catch (Exception e) {
            logger.error("Failed to deduct message from client: ", e.getMessage());
            message.setStatus(StatusType.E);
            throw e;
        }
    }

    private Message getMessageById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException());
    }


}
