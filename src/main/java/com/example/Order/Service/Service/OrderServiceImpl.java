package com.example.Order.Service.Service;


import com.example.Order.Service.Entity.Order;
import com.example.Order.Service.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServiceInterface {

    @Autowired
    private final OrderRepository orderRepository;

    @Override
    public String saveOrder(Order order) {
        orderRepository.save(order);
        return "Order Successfully";
    }
}
