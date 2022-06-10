package com.example.springrabbitmq.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MessageConfig {

    public static final String QUEUE_A = "mani_soni_a_queue";
    public static final String QUEUE_B = "mani_soni_b_queue";
    public static final String QUEUE_C = "mani_soni_c_queue";
    public static final String EXCHANGE = "mani_soni_exchange";
    public static final String ROUTING_KEY_ONE = "mani_soni_one_routingKey";
    public static final String ROUTING_KEY_TWO = "mani_soni_two_routingKey";
    public static final String ROUTING_KEY_THREE = "mani_soni_three_routingKey";

    // this is queue
    @Bean
    public Queue queueC() {
        return new Queue(QUEUE_C);
    }

    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A);
    }

    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B);
    }

    // This is exchange
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingA(Queue queueA, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueA).to(topicExchange).with(ROUTING_KEY_ONE);
    }

    @Bean
    public Binding bindingB(Queue queueB, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueB).to(topicExchange).with(ROUTING_KEY_TWO);
    }

    @Bean
    public Binding bindingC(Queue queueC, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueC).to(topicExchange).with(ROUTING_KEY_THREE);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
