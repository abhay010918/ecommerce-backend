package com.ecommerce.backend.service.impl;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.CartItem;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;
import com.ecommerce.backend.service.CartService;

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findById(userId)
                .orElseGet(
                        () -> {
                            User user = userRepository.findById(userId).orElseThrow();
                            Cart cart = new Cart();
                            cart.setUser(user);
                            return cartRepository.save(cart);
                        }
                );
    }

    @Override
    public Cart addItemToCart(Long userId, Long productId, int quantity) {

        Cart cart = getCartByUserId(userId);
        Product product = productRepository.findById(productId).orElseThrow();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemFromCart(Long userId, Long productId) {

        Cart cart = getCartByUserId(userId);
        cart.getCartItems()
                .removeIf(item -> item.getProduct().getId().equals(productId));
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long userId) {

        Cart cart = getCartByUserId(userId);
        cart.getCartItems().clear();
        cartRepository.save(cart);

    }
}
