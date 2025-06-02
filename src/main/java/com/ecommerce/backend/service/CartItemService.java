package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.CartItem;

public interface CartItemService {
    CartItem updateCartItemQuantity(Long cartItemId, int quantity);
}
