package com.example.ecommerce.eventdriven.order;

import com.example.ecommerce.eventdriven.commons.events.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@RequiredArgsConstructor
@Service
class OrderService {

    private final OrderRepository orderRepository;
    private final EventPublisher publisher;

    public Order createOrder() {
        Order order = new Order();
        orderRepository.save(order);
        return order;
    }

    public void completeOrder(UUID orderId) {
        Order order = orderRepository.getById(orderId);
        OrderCompleted orderCompleted = order.complete();
        publisher.publish(orderCompleted);
    }

    public void addToOrder(UUID orderId, UUID productId, long amount) {
        Order order = orderRepository.getById(orderId);
        order.addToOrder(productId, amount);
    }

    public void removeFromOrder(UUID orderId, UUID productId, long amount) {
        Order order = orderRepository.getById(orderId);
        order.removeFromOrder(productId, amount);
    }
}
