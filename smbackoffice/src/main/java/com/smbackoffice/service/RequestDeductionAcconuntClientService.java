package com.smbackoffice.service;

import com.smbackoffice.dto.request.DeductionRequestDTO;
import com.smbackoffice.entity.Message;
import com.smbackoffice.exception.MessageNotFoundException;
import com.smbackoffice.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestDeductionAcconuntClientService {

    private static final Logger logger = LoggerFactory.getLogger(RequestDeductionAcconuntClientService.class);

    private MessageRepository messageRepository;
    private AccountPreService accountPreService;

    public RequestDeductionAcconuntClientService(MessageRepository messageRepository, AccountPreService accountPreService) {
        this.messageRepository = messageRepository;
        this.accountPreService = accountPreService;
    }

    public void deductionAccount(DeductionRequestDTO withdrawRequestDTO) {

        accountPreService.deduceValueFromAccount(withdrawRequestDTO);

    }

    private Message getMessageById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException());
    }


}
