package com.project.springbootrabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {
    //Logger instance to parse the message
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);
}