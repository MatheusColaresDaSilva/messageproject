package com.smsender.service;

import com.smsender.config.PayloadSmSenderDTO;
import com.smsender.config.SmSenderQueueRaabitMQConfig;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageProducerService {

    private RabbitTemplate rabbitTemplate;

    private DirectExchange exchange;

    public void sendMessage(PayloadSmSenderDTO payload) {
        rabbitTemplate.convertAndSend(exchange.getName(), SmSenderQueueRaabitMQConfig.EXCHANGE_ROUTINGKEY, payload);
    }

    public PayloadSmSenderDTO createPayload(Long idClient, Long idPlan, Long idMessage) {
        return PayloadSmSenderDTO.builder().idClient(idClient).idPlan(idPlan).idMessage(idMessage).build();
    }

}
