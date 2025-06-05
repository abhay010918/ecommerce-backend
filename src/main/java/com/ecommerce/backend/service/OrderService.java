package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO placeOrder(Long userId, OrderDTO orderDTO);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getOrdersByUserId(Long userId);
    void cancelOrder(Long id);
    List<OrderDTO> getAllOrders();
    OrderDTO updateOrderStatus(Long id, String status);
}
