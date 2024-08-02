package com.smconsumer.service;

import com.smconsumer.dto.PayloadSmSenderDTO;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerSmQueueService {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "smsender_message", durable = "true"),
            exchange = @Exchange(value = "smsender_message_exchange"),
            key = "smsender_message_routingkey")
    )
    public void processOrder(PayloadSmSenderDTO order) {
        System.out.println( "do something");
    }
}
