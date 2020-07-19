package com.example.ecommerce.eventdriven.catalog

import com.example.ecommerce.eventdriven.commons.events.EventPublisher
import spock.lang.Specification

class CatalogServiceTest extends Specification {

    Catalog catalog = new InMemoryProductRepository()
    EventPublisher publisher = Mock(EventPublisher.class)
    CatalogService catalogService = new CatalogService(catalog, publisher)

    def "should create new product"() {
        given:
        def name = 'product'
        def price = BigDecimal.valueOf(10)
        when:
        catalogService.create(name, price)
        then:
        catalog.findAll().size() == 1
        catalog.findAll().get(0).getName() == name
    }

    def "should publish event when new product"() {
        given:
        def name = 'product'
        def price = BigDecimal.valueOf(10)
        when:
        catalogService.create(name, price)
        then:
        1* publisher.publish(_)
    }
}
