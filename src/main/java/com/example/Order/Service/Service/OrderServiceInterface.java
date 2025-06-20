package com.example.Order.Service.Service;

import com.example.Order.Service.Entity.Order;

public interface OrderServiceInterface {
    public Order placeOrder(int quantity, Long userId, Long productId);
}
