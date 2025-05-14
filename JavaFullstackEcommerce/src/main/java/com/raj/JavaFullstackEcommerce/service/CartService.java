package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.Request.AddItemRequest;
import com.raj.JavaFullstackEcommerce.exception.ProductException;
import com.raj.JavaFullstackEcommerce.model.Cart;
import com.raj.JavaFullstackEcommerce.model.User;

public interface CartService {

    public Cart createCart(User user);
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}
