package com.example.Order.Service.Controller;


import com.example.Order.Service.Entity.Order;
import com.example.Order.Service.Service.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order/v1")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderServiceImpl orderService;

    @PostMapping("create-order")
    public ResponseEntity<String> createOrder(Order order){
        String response = orderService.saveOrder(order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("place-order")
    private ResponseEntity<Order> placeOrder(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ){
        Order order = orderService.placeOrder(quantity, userId, productId);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

}
