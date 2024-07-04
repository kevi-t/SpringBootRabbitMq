package com.project.springbootrabbitmq.consumer;

import com.project.springbootrabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void jsonConsumer(User user){
        LOGGER.info(String.format("Received JSON message -> %s",user.toString()));
    }
}