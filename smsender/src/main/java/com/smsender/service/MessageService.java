package com.smsender.service;

import com.smsender.config.PayloadSmSenderDTO;
import com.smsender.dto.request.MessageRequestDTO;
import com.smsender.dto.response.MessageResponseDTO;
import com.smsender.entity.Message;
import com.smsender.enums.StatusType;
import com.smsender.exception.MessageNotFoundException;
import com.smsender.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.amqp.AmqpException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.smsender.converter.MessageConverter.*;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private MessageRepository messageRepository;
    private MessageProducerService messageProducerService;

    public MessageService(MessageRepository messageRepository, MessageProducerService messageProducerService) {
        this.messageRepository = messageRepository;
        this.messageProducerService = messageProducerService;
    }

    public Page<MessageResponseDTO> findAll(Pageable page) {
        List<MessageResponseDTO> clientResponse = new ArrayList<>();

        Page<Message> messages = messageRepository.findAll(page);
        messages.forEach(message -> clientResponse.add(messageEntityToDto(message)));

        return  new PageImpl<>(clientResponse, page, messages.getTotalElements()) ;

    }

    public MessageResponseDTO findById(Long id) {
        final Message message = getMessageById(id);
        return messageEntityToDto(message);
    }

    @Transactional
    public MessageResponseDTO createNewMessage(MessageRequestDTO messageRequestDTO) {
        //TODO VALIDATIONS
        final Message message = messageDtoToEntity(messageRequestDTO);
        return addMessageInTheQueue(messageRequestDTO, message);
    }

    @Transactional
    private MessageResponseDTO addMessageInTheQueue(MessageRequestDTO messageRequestDTO, Message message) {
        Message messageSaved = null;
        boolean isError = false;

        try {
                messageSaved = messageRepository.save(message);
                message.setId(messageSaved.getId());
                PayloadSmSenderDTO payload = messageProducerService.createPayload(messageRequestDTO.getClientId(), messageRequestDTO.getPlanId(), messageSaved.getId());
                messageProducerService.sendMessage(payload);
                messageEntityToDto(messageSaved);
            } catch (AmqpException e) {
                logger.error("Failed to send message to RabbitMQ: {}", e.getMessage());
                isError = true;
            } catch (Exception e) {
                logger.error("An unexpected error occurred: {}", e.getMessage());
                isError = true;
            }

            if (isError && messageSaved != null) {
                messageSaved.setStatus(StatusType.E);
                messageRepository.save(messageSaved);
            }

            return messageSaved != null ? messageEntityToDto(messageSaved) : null;

    }

    private Message getMessageById(Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException());
    }


}
