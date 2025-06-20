package com.example.Order.Service.Service;


import com.example.Order.Service.Client.ProductClient;
import com.example.Order.Service.Entity.Order;
import com.example.Order.Service.Model.ProductDto;
import com.example.Order.Service.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServiceInterface {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ProductClient productClient;

    public String saveOrder(Order order) {
        orderRepository.save(order);
        return "Order Successfully";
    }

    @Override
    public Order placeOrder(int quantity, Long userId, Long productId) {
        ProductDto product = productClient.getProductById(productId);

        if(product.getQuantity()<quantity){
            throw new RuntimeException("Insufficient product quantity");
        }
        Order order = Order.builder()
                .productId(product.getProductId())
                .userId(userId)
                .quantity(quantity)
                .totalPrice(product.getPrice()*quantity)
                .address("Bangalore")
                .build();

        return orderRepository.save(order);
    }
}
