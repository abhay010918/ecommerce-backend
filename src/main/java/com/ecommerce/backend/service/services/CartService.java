package com.ecommerce.backend.service.services;

import com.ecommerce.backend.dto.CartDTO;

public interface CartService {
    CartDTO getCartByUserId(Long userId);
    CartDTO addItemToCart(Long userId, Long productId, int quantity);
    CartDTO removeItemFromCart(Long userId, Long productId);
    void clearCart(Long userId);
    CartDTO updateItemQuantity(Long userId, Long productId, int quantity);
}
