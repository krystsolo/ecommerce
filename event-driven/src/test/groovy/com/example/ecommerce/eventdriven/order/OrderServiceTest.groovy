package com.example.ecommerce.eventdriven.order

import com.example.ecommerce.eventdriven.commons.events.EventPublisher
import spock.lang.Specification

class OrderServiceTest extends Specification {

    EventPublisher publisher = Mock(EventPublisher.class)
    OrderRepository orderRepository = new InMemoryOrderRepository()
    OrderService orderService = new OrderService(orderRepository, publisher)

    def "new order created"() {
        when:
            Order order = orderService.createOrder()
        then:
            orderRepository.getById(order.id) != null
    }

    def "complete order"() {
        given:
            Order order = orderService.createOrder()
            order.addToOrder(UUID.randomUUID(), 5)
        when:
            orderService.completeOrder(order.id)
        then:
            orderRepository.getById(order.id).status == Order.Status.Completed
    }

    def "event is published when order completed"() {
        given:
        Order order = orderService.createOrder()
        order.addToOrder(UUID.randomUUID(), 5)
        when:
        orderService.completeOrder(order.id)
        then:
        1 * publisher.publish(_ as OrderCompleted)
    }

    def "cannot complete order when is empty"() {
        given:
            Order order = orderService.createOrder()
        when:
            orderService.completeOrder(order.id)
        then:
            thrown(IllegalStateException)
    }

    def "add product to order"() {
        given:
            Order order = orderService.createOrder()
        when:
            orderService.addToOrder(order.id, UUID.randomUUID(), 5)
        then:
            orderRepository.getById(order.id).orderItems.size() == 1
    }
}
