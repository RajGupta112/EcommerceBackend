package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.exception.OrderException;
import com.raj.JavaFullstackEcommerce.model.Address;
import com.raj.JavaFullstackEcommerce.model.Order;
import com.raj.JavaFullstackEcommerce.model.User;
import com.raj.JavaFullstackEcommerce.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImplimentation implements OrderService {

    private CartRepository cartRepository;

    private CartItemService cartItemService;

    private ProductService productService;
    @Override
    public Order createOrder(User user, Address address) throws OrderException {
        return null;
    }

    @Override
    public Order findByOrderId(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public List<Order> usersOrderHistory(Long userId) {
        return List.of();
    }

    @Override
    public Order placeOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order confirmOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order deleviredOrder(Long orderId) throws OrderException {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId) throws OrderException {
        return null;
    }
}
