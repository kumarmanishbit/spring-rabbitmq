package com.example.springrabbitmq.consumer;

import com.example.springrabbitmq.config.MessageConfig;
import com.example.springrabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessageConfig.QUEUE_A)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue at customer end : " + orderStatus);
    }
}