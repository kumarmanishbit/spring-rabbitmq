package com.example.springrabbitmq.consumer;

import com.example.springrabbitmq.config.MessageConfig;
import com.example.springrabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WareHouse {

    @RabbitListener(queues = MessageConfig.QUEUE_C)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message recieved from queue At warehouse : " + orderStatus);
    }
}