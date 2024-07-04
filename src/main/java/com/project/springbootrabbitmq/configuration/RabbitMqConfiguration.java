package com.project.springbootrabbitmq.configuration;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.routing.json.key")
    private String jsonRoutingKey;

    //Spring bean for Rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    //Spring bean of queue to store json messages
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
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

    //Spring bean for binding between jsonQueue and exchange using routing key
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder.bind(jsonQueue()).to(exchange()).with(jsonRoutingKey);
    }

    //RabbitTemplate for the json message since this can not be autoconfigured
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    //Spring bean autoconfiguration with automatically configure this 3 beans by default : ConnectionFactory,RabbitTemplate,RabbitAdmin
}