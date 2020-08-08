package com.example.ecommerce.eventdriven.inventory

import spock.lang.Specification

class ItemTest extends Specification {

    public static final int AMOUNT = 10

    def "should reduce stock"() {
        given:
        Item item = new Item(UUID.randomUUID())
        when:
        item.reduceStockBy(AMOUNT)
        then:
        item.getAmount() == -1 * AMOUNT
    }

    def "should return massage about insufficient stock for product"() {
        given:
        Item item = new Item(UUID.randomUUID())
        when:
        def events = item.reduceStockBy(AMOUNT)
        then:
        assert events.any{event -> event instanceof StockEvent.InsufficientStockIssued && event.amount == AMOUNT}
    }

    def "should return massage about product taken from inventory"() {
        given:
        Item item = new Item(UUID.randomUUID())
        item.increaseStockBy(AMOUNT)
        when:
        def events = item.reduceStockBy(AMOUNT)
        then:
        assert events.any{event -> event instanceof StockEvent.ItemTakenFromStock && event.amount == AMOUNT}
    }

    def "should increase the item stock"() {
        given:
        Item item = new Item(UUID.randomUUID())
        when:
        item.increaseStockBy(AMOUNT)
        then:
        item.getAmount() == AMOUNT
    }
}
