package com.example.Order.Service.Service;

import com.example.Order.Service.Entity.Order;
import com.example.Order.Service.Model.OrderDto;
import com.example.Order.Service.Model.PlaceOrderDto;

import java.util.List;

public interface OrderServiceInterface {
    public Order placeOrder(int quantity, Long userId, Long productId);
    public Order placeOrderDynamically(PlaceOrderDto placeOrderDto, Long userId, Long productId);
    public List<OrderDto> findAllOrderByUserId(Long userId);
    public List<OrderDto> findAllOrders();
    public OrderDto findOrderById(Long userId, Long productId);
}
