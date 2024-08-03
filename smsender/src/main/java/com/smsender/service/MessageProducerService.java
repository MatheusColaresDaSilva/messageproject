package com.smsender.service;

import com.smsender.config.PayloadSmSenderDTO;
import com.smsender.config.SmSenderQueueRaabitMQConfig;
import com.smsender.entity.ClientPlan;
import com.smsender.exception.ClientNotFoundException;
import com.smsender.repository.ClientPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageProducerService {

    private RabbitTemplate rabbitTemplate;

    private DirectExchange exchange;

    private ClientPlanRepository clientPlanRepository;

    public void sendMessage(PayloadSmSenderDTO payload) {
        rabbitTemplate.convertAndSend(exchange.getName(), SmSenderQueueRaabitMQConfig.EXCHANGE_ROUTINGKEY, payload);
    }

    public PayloadSmSenderDTO createPayload(Long idClient, Long idPlan, Long idMessage) {

        ClientPlan clientPlan = clientPlanRepository.findClientPlanByClientIdAndPlanId(idClient, idPlan).orElseThrow(() -> new ClientNotFoundException());
        return PayloadSmSenderDTO.builder().idAccount(clientPlan.getAccount().getId()).idMessage(idMessage).build();
    }

}
