package com.nnroad.payroll.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String PAYROLL_EXCHANGE_NAME = "payroll";

    public static final String PAYROLL_ROUTING_KEY = "payroll.confirmed";

    @Value("${spring.rabbitmq.queue_name.payroll.confirm}")
    public String payrollQueueName;

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(payrollQueueName).build();
    }

    @Bean
    public DirectExchange exchange() {
        return ExchangeBuilder.directExchange(PAYROLL_EXCHANGE_NAME).durable(true).build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(PAYROLL_ROUTING_KEY);
    }
}
