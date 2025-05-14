package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.exception.CartItemException;
import com.raj.JavaFullstackEcommerce.exception.UserException;
import com.raj.JavaFullstackEcommerce.model.Cart;
import com.raj.JavaFullstackEcommerce.model.CartItem;
import com.raj.JavaFullstackEcommerce.model.Product;
import com.raj.JavaFullstackEcommerce.model.User;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId,Long id, CartItem cartItem)throws CartItemException, UserException;

    public CartItem isCartItemExist(Cart cart, Product product,String size,Long userId);

    public  void removeCartItem(Long userId,Long cartItemId)throws CartItemException,UserException;

    public  CartItem findCartItemById(Long cartItemId) throws CartItemException;

}
