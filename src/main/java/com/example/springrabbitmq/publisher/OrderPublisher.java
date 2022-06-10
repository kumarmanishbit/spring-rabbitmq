package com.example.springrabbitmq.publisher;

import com.example.springrabbitmq.config.MessageConfig;
import com.example.springrabbitmq.dto.Order;
import com.example.springrabbitmq.dto.OrderStatus;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private TopicExchange topicExchange;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        //restaurantservice
        //payment service
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
        template.convertAndSend(topicExchange.getName(), MessageConfig.ROUTING_KEY_ONE, orderStatus);
        template.convertAndSend(topicExchange.getName(), MessageConfig.ROUTING_KEY_TWO, orderStatus);
        template.convertAndSend(topicExchange.getName(), MessageConfig.ROUTING_KEY_THREE, orderStatus);
        return "Success !!";
    }
}