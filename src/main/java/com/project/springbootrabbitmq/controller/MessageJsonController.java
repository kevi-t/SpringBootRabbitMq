package com.project.springbootrabbitmq.controller;

import com.project.springbootrabbitmq.dto.User;
import com.project.springbootrabbitmq.publisher.RabbitMqJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/springRabbitMq")
public class MessageJsonController {
    private final RabbitMqJsonProducer rabbitMqJsonProducer;

    public MessageJsonController(RabbitMqJsonProducer rabbitMqJsonProducer) {
        this.rabbitMqJsonProducer = rabbitMqJsonProducer;
    }
    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        rabbitMqJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message sent to RabbitMq....");
    }
}