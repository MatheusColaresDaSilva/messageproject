package com.smconsumer.service;

import com.smconsumer.dto.PayloadSmSenderDTO;
import com.smconsumer.feign.BackOfficeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@Service
public class ConsumerSmQueueService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerSmQueueService.class);

    private BackOfficeClient backOfficeClient;

    public ConsumerSmQueueService(BackOfficeClient backOfficeClient) {
        this.backOfficeClient = backOfficeClient;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "smsender_message", durable = "true"),
            exchange = @Exchange(value = "smsender_message_exchange"),
            key = "smsender_message_routingkey")
    )
    public void processOrder(PayloadSmSenderDTO payload) {
        try {
            logger.info("Processing payload: {}", payload);
            backOfficeClient.evaluateRequestMessage(payload);
        } catch (HttpClientErrorException e) {
            logger.error("HTTP client error while processing payload: Status {} - {}", e.getStatusCode(), e.getMessage());
        } catch (RestClientException e) {
            logger.error("REST client error while processing payload: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("An unexpected error occurred while processing payload: {}", e.getMessage());
        }
    }
}
