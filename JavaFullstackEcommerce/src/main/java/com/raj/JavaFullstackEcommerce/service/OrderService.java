package com.raj.JavaFullstackEcommerce.service;

import com.raj.JavaFullstackEcommerce.exception.OrderException;
import com.raj.JavaFullstackEcommerce.model.Address;
import com.raj.JavaFullstackEcommerce.model.Order;
import com.raj.JavaFullstackEcommerce.model.User;

import java.util.List;

public interface OrderService {

    public Order createOrder(User user, Address address)throws OrderException;

    public Order findByOrderId(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public  Order placeOrder(Long orderId)throws OrderException;

    public Order confirmOrder(Long orderId)throws OrderException;


    public Order shippedOrder(Long orderId)throws OrderException;


    public Order deleviredOrder(Long orderId)throws OrderException;


    public Order cancelOrder(Long orderId)throws OrderException;
}
