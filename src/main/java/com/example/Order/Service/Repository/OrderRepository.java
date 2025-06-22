package com.example.Order.Service.Repository;


import com.example.Order.Service.Entity.Order;
import com.example.Order.Service.Model.OrderDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public Optional<Order> findByUserIdAndProductId(Long userId, Long productId);
    public Optional<List<Order>> findByUserId(Long userId);
}
