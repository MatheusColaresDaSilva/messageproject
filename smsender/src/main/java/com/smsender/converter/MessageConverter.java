package com.smsender.converter;

import com.smsender.dto.request.MessageRequestDTO;
import com.smsender.dto.response.ClientResponseDTO;
import com.smsender.dto.response.MessageResponseDTO;
import com.smsender.entity.Client;
import com.smsender.entity.Message;
import com.smsender.enums.StatusType;

public abstract class MessageConverter {

        public static Message messageDtoToEntity(MessageRequestDTO messageRequestDTO) {
            return messageDtoToEntity(messageRequestDTO, StatusType.P);
        }

        public static Message messageDtoToEntity(MessageRequestDTO messageRequestDTO, StatusType status) {
            return Message.builder()
                    .content(messageRequestDTO.getContent())
                    .recipientPhoneNumber(messageRequestDTO.getRecipientPhoneNumber())
                    .isWhats(messageRequestDTO.getIsWhats())
                    .status(status)
                    .build();
        }

        public static MessageResponseDTO messageEntityToDto(Message message) {
            return MessageResponseDTO.builder()
                    .id(message.getId())
                    .content(message.getContent())
                    .recipientPhoneNumber(message.getRecipientPhoneNumber())
                    .isWhats(message.getIsWhats())
                    .status(message.getStatus().getValue())
                    .build();
        }

}
