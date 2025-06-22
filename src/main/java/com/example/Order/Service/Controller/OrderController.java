package com.example.Order.Service.Controller;


import com.example.Order.Service.Entity.Order;
import com.example.Order.Service.Model.OrderDto;
import com.example.Order.Service.Model.PlaceOrderDto;
import com.example.Order.Service.Service.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order/v1")
@RequiredArgsConstructor
@Tag(
        name = "Order Controller",
        description = "Order Controller is used to manage orders in the Order Service. " +
                "It provides endpoints to place orders, retrieve orders by user, and get all orders."
)
public class OrderController {

    @Autowired
    private final OrderServiceImpl orderService;

    @Operation(
            summary = "Place an Order",
            description = "This endpoint allows users to place an order by providing user ID, product ID, and quantity."
    )
    @ApiResponse(
            responseCode = "201",
            description = "CREATED: Order placed successfully and returned in the response."
    )
    @PostMapping("place-order")
    private ResponseEntity<Order> placeOrder(
            @Parameter(
                    description = "ID of the user placing the order",
                    example = "1",
                    required = true
            )
            @RequestParam Long userId,
            @Parameter(
                    description = "ID of the product being ordered",
                    example = "1",
                    required = true
            )
            @RequestParam Long productId,
            @Parameter(
                    description = "Quantity of the product that has to be ordered",
                    example = "2",
                    required = true
            )
            @RequestParam int quantity
    ){
        Order order = orderService.placeOrder(quantity, userId, productId);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @Operation(
            summary = "Place an Order Dynamically",
            description = "This endpoint allows users to place an order dynamically by providing user ID, product ID, and order details."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Created: Order placed successfully and returned in the response."
    )
    @PostMapping("place-order-dto")
    private ResponseEntity<Order> placeOrderDto(
            @Parameter(
                    description = "ID of the user placing the order",
                    example = "1",
                    required = true
            )
            @RequestParam Long userId,
            @Parameter(
                    description = "ID of the product being ordered",
                    example = "1",
                    required = true
            )
            @RequestParam Long productId,
            @Parameter(
                    description = "Details of the order including quantity, address, city, state, and pin code",
                    required = true
            )
            @RequestBody PlaceOrderDto placeOrderDto
            ){
        Order order = orderService.placeOrderDynamically(placeOrderDto, userId, productId);
        return new ResponseEntity<>(order,HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Orders",
            description = "This endpoint retrieves all orders placed in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK: Returns the list of all orders."
    )
    @GetMapping("get-all-orders")
    private ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.findAllOrders());
    }


    @Operation(
            summary = "Get All Orders by User",
            description = "This endpoint retrieves all orders placed by a specific user."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK: Returns a list of all orders placed by the user."
    )
    @GetMapping("get-all-orders-by-user/{userId}")
    private ResponseEntity<List<OrderDto>> getAllOrdersByUser(@Parameter(description = "ID of the user whose orders are to be retrieved", example = "1", required = true)
                                                              @PathVariable Long userId){
        return ResponseEntity.ok(orderService.findAllOrderByUserId(userId));
    }

    @Operation(
            summary = "Get Order by ID",
            description = "This endpoint retrieves a specific order by user ID and product ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK: Returns the order details for the specified user and product."
    )
    @GetMapping("get-order-by-id")
    private ResponseEntity<OrderDto> getOrderById(@Parameter(description = "ID of the user who placed the order", example = "1", required = true)
                                                  @RequestParam Long userId,
                                                  @Parameter(description = "ID of the product being ordered", example = "1", required = true)
                                                  @RequestParam Long productId
    ){
        return ResponseEntity.ok(orderService.findOrderById(userId, productId));
    }
}
