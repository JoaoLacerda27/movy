package com.movy.shared.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfiguration {
    public static final String DELIVERY_EVENTS_QUEUE = "delivery.events";

    @Bean
    public Queue deliveryEventsQueue() {
        return new Queue(DELIVERY_EVENTS_QUEUE, true);
    }
}
