package com.example.Order.Service.Service;


import com.example.Order.Service.Client.ProductClient;
import com.example.Order.Service.Client.UserClient;
import com.example.Order.Service.Entity.Order;
import com.example.Order.Service.Model.OrderDto;
import com.example.Order.Service.Model.PlaceOrderDto;
import com.example.Order.Service.Model.ProductDto;
import com.example.Order.Service.Model.UserDto;
import com.example.Order.Service.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServiceInterface {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ProductClient productClient;

    @Autowired
    private final UserClient userClient;


    @Override
    public Order placeOrder(int quantity, Long userId, Long productId) {
        UserDto user = userClient.findUserById(userId);
        ProductDto product = productClient.getProductById(productId);

        if(product.getQuantity()<quantity){
            throw new RuntimeException("Insufficient product quantity");
        }
        Order order = Order.builder()
                .productId(product.getProductId())
                .userId(userId)
                .quantity(quantity)
                .totalPrice(product.getPrice()*quantity)
                .address(user.getAddress())
                .city(user.getCity())
                .state(user.getState())
                .pinCode(user.getPinCode())
                .build();
        ProductDto productDto = new ProductDto();
        productDto.setQuantity(quantity);
        productClient.updateProductQuantity(productId, productDto);
        return orderRepository.save(order);
    }

    @Override
    public Order placeOrderDynamically(PlaceOrderDto placeOrderDto, Long userId, Long productId) {
        int quantity = placeOrderDto.getQuantity();
        UserDto user = userClient.findUserById(userId);
        ProductDto product = productClient.getProductById(productId);

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient product quantity");
        }

        Order order = Order.builder()
                .productId(product.getProductId())
                .userId(userId)
                .quantity(quantity)
                .totalPrice(product.getPrice() * quantity)
                .address(placeOrderDto.getAddress())
                .city(placeOrderDto.getCity())
                .state(placeOrderDto.getState())
                .pinCode(placeOrderDto.getPinCode())
                .build();

        ProductDto productDto = new ProductDto();
        productDto.setQuantity(quantity);
        productClient.updateProductQuantity(productId, productDto);

        return orderRepository.save(order);
    }


    @Override
    public List<OrderDto> findAllOrderByUserId(Long userId) {
        UserDto userById = userClient.findUserById(userId);
        if(userById == null){
            throw new RuntimeException("User not found");
        }

        List<Order> orders = orderRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Order not found for userId: " + userId));
        return orders.stream().map(order ->{
                ProductDto productById = productClient.getProductById(order.getProductId());

               return OrderDto.builder()
                .orderId(order.getOrderId())
                .userId(userById.getUserId())
                .userName(userById.getFirstName() + " " + userById.getLastName())
                .email(userById.getEmail())
                .productId(order.getProductId())
                .productName(productById.getProductName())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .description(productById.getDescription())
                .orderDate(order.getOrderDate())
                .address(userById.getAddress())
                .city(userById.getCity())
                .state(userById.getState())
                .pinCode(userById.getPinCode())
                .build();
        }).collect(Collectors.toList());
    }

    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map( order -> {
            UserDto userById = userClient.findUserById(order.getUserId());
            ProductDto productById = productClient.getProductById(order.getProductId());

            return OrderDto.builder()
                    .orderId(order.getOrderId())
                    .userId(userById.getUserId())
                    .userName(userById.getFirstName() + " " + userById.getLastName())
                    .email(userById.getEmail())
                    .productId(productById.getProductId())
                    .productName(productById.getProductName())
                    .quantity(order.getQuantity())
                    .totalPrice(productById.getPrice() * productById.getQuantity())
                    .description(productById.getDescription())
                    .orderDate(order.getOrderDate())
                    .address(userById.getAddress())
                    .city(userById.getCity())
                    .state(userById.getState())
                    .pinCode(userById.getPinCode())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public OrderDto findOrderById(Long userId, Long productId) {
        UserDto foundUser = userClient.findUserById(userId);
        ProductDto foundProduct = productClient.getProductById(productId);
        Order order = orderRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new RuntimeException("Order not found for userId: " + userId + " and productId: " + productId));

        return OrderDto.builder()
                .orderId(order.getOrderId())
                .userId(foundUser.getUserId())
                .userName(foundUser.getFirstName() + " " + foundUser.getLastName())
                .email(foundUser.getEmail())
                .productId(foundProduct.getProductId())
                .productName(foundProduct.getProductName())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .description(foundProduct.getDescription())
                .orderDate(order.getOrderDate())
                .address(foundUser.getAddress())
                .city(foundUser.getCity())
                .state(foundUser.getState())
                .pinCode(foundUser.getPinCode())
                .build();

    }

}
