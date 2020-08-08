package com.example.ecommerce.eventdriven.inventory;

import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
class UpdateStockCommand {
    List<UpdatedItem> updatedItem;
}

@Value
class UpdatedItem {
    UUID productId;
    long amount;
}