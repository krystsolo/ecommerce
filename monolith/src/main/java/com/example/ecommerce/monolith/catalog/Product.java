package com.example.ecommerce.monolith.catalog;

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
    private @Id UUID id = UUID.randomUUID();
    private String name;
    private Status status = Status.Available;
    private BigDecimal price;
}


