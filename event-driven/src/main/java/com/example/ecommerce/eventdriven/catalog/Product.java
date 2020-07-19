package com.example.ecommerce.eventdriven.catalog;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Setter(AccessLevel.NONE)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
class Product {

    enum Status {
        Available, Archived
    }
    private @Id UUID id;
    private String name;
    private Status status;
    private BigDecimal price;

    Product(String name, BigDecimal price) {
        this.id = UUID.randomUUID();
        this.status = Status.Available;
        this.name = name;
        this.price = price;
    }
}


