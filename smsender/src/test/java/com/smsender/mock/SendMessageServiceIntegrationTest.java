package com.smsender.mock;

import com.smsender.config.PayloadSmSenderDTO;
import com.smsender.dto.request.MessageRequestDTO;
import com.smsender.dto.response.MessageResponseDTO;
import com.smsender.entity.Message;
import com.smsender.enums.StatusType;
import com.smsender.repository.MessageRepository;
import com.smsender.service.MessageProducerService;
import com.smsender.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SendMessageServiceIntegrationTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private MessageProducerService messageProducerService;

    @Test
    public void testCreateNewMessage() {
        MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
        messageRequestDTO.setClientId(1L);
        messageRequestDTO.setPlanId(1L);
        messageRequestDTO.setContent("THIS IS A TEST");
        messageRequestDTO.setRecipientPhoneNumber(123456);
        messageRequestDTO.setIsWhats(Boolean.FALSE);

        PayloadSmSenderDTO payload = new PayloadSmSenderDTO();
        when(messageProducerService.createPayload(1L, 1L, 1L)).thenReturn(payload);
        doNothing().when(messageProducerService).sendMessage(payload);

        Message message = new Message();
        message.setId(1L);
        message.setContent("THIS IS A TEST");
        message.setIsWhats(Boolean.FALSE);
        message.setRecipientPhoneNumber(1231);
        message.setStatus(StatusType.P);

        when(messageRepository.save(any(Message.class))).thenReturn(message);

        MessageResponseDTO responseDTO = messageService.createNewMessage(messageRequestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getId()).isEqualTo(1L);
        assertThat(responseDTO.getContent()).isEqualTo("THIS IS A TEST");
        assertThat(responseDTO.getIsWhats()).isFalse();
        assertThat(responseDTO.getRecipientPhoneNumber()).isEqualTo(1231);
        assertThat(responseDTO.getStatus()).isEqualTo(StatusType.P.getValue());


        verify(messageRepository).save(any(Message.class));
        verify(messageProducerService).createPayload(1L, 1L, 1L);
        verify(messageProducerService).sendMessage(payload);
    }


    @Test
    public void testCreateNewMessageWithError() {
        MessageRequestDTO messageRequestDTO = new MessageRequestDTO();
        messageRequestDTO.setClientId(1L);
        messageRequestDTO.setPlanId(1L);
        messageRequestDTO.setContent("THIS IS A TEST");
        messageRequestDTO.setRecipientPhoneNumber(123456);
        messageRequestDTO.setIsWhats(Boolean.FALSE);

        PayloadSmSenderDTO payload = new PayloadSmSenderDTO();
        when(messageProducerService.createPayload(1L, 1L, 1L)).thenReturn(payload);
        doNothing().when(messageProducerService).sendMessage(payload);

        Message message = new Message();
        message.setId(1L);
        message.setContent("THIS IS A TEST");
        message.setIsWhats(Boolean.FALSE);
        message.setRecipientPhoneNumber(1231);

        when(messageRepository.save(any(Message.class))).thenReturn(message);

        MessageResponseDTO responseDTO = messageService.createNewMessage(messageRequestDTO);

        assertThat(responseDTO).isNotNull();
        assertThat(responseDTO.getId()).isEqualTo(1L);
        assertThat(responseDTO.getContent()).isEqualTo("THIS IS A TEST");
        assertThat(responseDTO.getIsWhats()).isFalse();
        assertThat(responseDTO.getRecipientPhoneNumber()).isEqualTo(1231);
        assertThat(responseDTO.getStatus()).isEqualTo(StatusType.E.getValue());


        verify(messageRepository, times(2)).save(any(Message.class));
        verify(messageProducerService).createPayload(1L, 1L, 1L);
        verify(messageProducerService).sendMessage(payload);
    }
}