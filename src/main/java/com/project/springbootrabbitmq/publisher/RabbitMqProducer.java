package com.project.springbootrabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    //Logger instance to parse the message
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    //Method to send the message
    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s",message));
        //Using covert method to send message to the exchange and exchange uses the routing key to send the message to the Queue
        rabbitTemplate.convertAndSend(exchange,routingKey,message);

    }
}