package com.example.ecommerce.eventdriven.commons.events.publisher;

import com.example.ecommerce.eventdriven.commons.events.DomainEvent;
import com.example.ecommerce.eventdriven.commons.events.EventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@AllArgsConstructor
class ForwardEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
