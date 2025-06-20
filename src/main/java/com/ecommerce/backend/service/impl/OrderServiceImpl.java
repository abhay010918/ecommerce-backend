package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.dto.OrderDTO;
import com.ecommerce.backend.entity.Order;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.enums.OrderStatus;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.service.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;



    public OrderServiceImpl(OrderRepository orderRepository,
                            UserRepository userRepository,
                            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public OrderDTO placeOrder(Long userId, OrderDTO orderDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));


        Product product = productRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(orderDTO.getTotalAmount());
        order.setStatus(orderDTO.getStatus());
        order.setOrderDate(orderDTO.getCreatedAt());
        // Add more fields as needed

        Order savedOrder = orderRepository.save(order);
        return mapToDTO(savedOrder);

    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order is not present: " + id));
        return mapToDTO(order);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found:"));
        order.setStatus(OrderStatus.valueOf(status));
        Order save = orderRepository.save(order);
        return mapToDTO(save);
    }

    private OrderDTO mapToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setCreatedAt(order.getOrderDate());
        dto.setTotalAmount(order.getTotalPrice());
        dto.setStatus(OrderStatus.valueOf(String.valueOf(order.getStatus())));
        return dto;
    }
}
