package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Order;
import java.util.List;

public interface OrderService {
    Order placeOrder(Long userId, Order order);
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    void cancelOrder(Long id);
}
