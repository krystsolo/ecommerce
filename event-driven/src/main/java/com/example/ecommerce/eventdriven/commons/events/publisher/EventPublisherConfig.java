package com.example.ecommerce.eventdriven.commons.events.publisher;

import com.example.ecommerce.eventdriven.commons.events.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EventPublisherConfig {

    @Bean
    EventPublisher eventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        return new LoggingEventPublisher(new ForwardEventPublisher(applicationEventPublisher));
    }
}
