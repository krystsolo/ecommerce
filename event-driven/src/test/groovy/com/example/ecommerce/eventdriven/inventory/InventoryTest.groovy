package com.example.ecommerce.eventdriven.inventory

import com.example.ecommerce.eventdriven.commons.events.EventPublisher
import spock.lang.Specification

class InventoryTest extends Specification {

    private static final int AMOUNT = 10

    EventPublisher eventPublisher = Mock(EventPublisher.class)
    ItemRepository repository = new InMemoryItemRepository()
    Inventory inventory = new Inventory(repository, eventPublisher)

    def "should add to item stock"() {
        given:
        def productId = UUID.randomUUID()
        inventory.createItem(productId)
        when:
        inventory.addToStock(productId, AMOUNT)
        then:
        inventory.getStockFor(productId) == AMOUNT
    }

    def "should create Item"() {
        given:
        def productId = UUID.randomUUID()
        when:
        inventory.createItem(productId)
        then:
        inventory.getStockFor(productId) == 0
    }

    def "should reduce stock for item"() {
        given:
        def productId = UUID.randomUUID()
        inventory.createItem(productId)
        inventory.addToStock(productId, AMOUNT)
        when:
        inventory.updateStockFor(new UpdateStockCommand(Arrays.asList(new UpdatedItem(productId, AMOUNT))))
        then:
        inventory.getStockFor(productId) == 0
    }

    def "should publish event when stock reduced for item"() {
        given:
        def productId = UUID.randomUUID()
        inventory.createItem(productId)
        inventory.addToStock(productId, AMOUNT)
        when:
        inventory.updateStockFor(new UpdateStockCommand(Arrays.asList(new UpdatedItem(productId, AMOUNT))))
        then:
        1 * eventPublisher.publish(_ as List<StockEvent.InsufficientStockIssued>)
    }
}
