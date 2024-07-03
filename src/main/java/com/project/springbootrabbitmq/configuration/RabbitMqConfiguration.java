package com.project.springbootrabbitmq.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    //Spring bean for Rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }

    //Spring bean for Rabbitmq Exchange
    @Bean
    public TopicExchange exchange(){
        return  new TopicExchange(exchange);
    }

    //Spring bean for binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
    }

    //Spring bean autoconfiguration with automatically configure this 3 beans: ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}