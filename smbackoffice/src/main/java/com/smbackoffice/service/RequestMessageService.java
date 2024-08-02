package com.smbackoffice.service;

import com.smbackoffice.entity.Message;
import com.smbackoffice.exception.MessageNotFoundException;
import com.smbackoffice.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestMessageService {

    private static final Logger logger = LoggerFactory.getLogger(RequestMessageService.class);

    private MessageRepository messageRepository;

    public RequestMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    private Message getMessageById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException());
    }


}
