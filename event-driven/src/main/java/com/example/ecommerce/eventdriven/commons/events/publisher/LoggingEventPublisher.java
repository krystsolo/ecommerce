package com.example.ecommerce.eventdriven.commons.events.publisher;

import com.example.ecommerce.eventdriven.commons.events.DomainEvent;
import com.example.ecommerce.eventdriven.commons.events.EventPublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
class LoggingEventPublisher implements EventPublisher {

    private final EventPublisher eventPublisher;

    @Override
    public void publish(DomainEvent event) {
        log.debug("Event published: {}", event);
        eventPublisher.publish(event);
    }
}
