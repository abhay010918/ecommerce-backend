package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.OrderItem;

public interface OrderItemService {
    OrderItem getOrderItemById(Long id);
}
