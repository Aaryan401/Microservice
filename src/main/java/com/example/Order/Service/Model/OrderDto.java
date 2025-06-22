package com.example.Order.Service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    @Schema(
            description = "Unique identifier for the order",
            example = "1"
    )
    private Long orderId;
    @Schema(
            description = "Unique identifier for the user who placed the order",
            example = "1"
    )
    private Long userId;
    @Schema(
            description = "Name of the user who placed the order",
            example = "Aaryan Prashar"
    )
    private String userName;
    @Schema(
            description = "Email of the user who placed the order",
            example = "aaryan9@gmail.com"
    )
    private String email;
   @Schema(
            description = "Id of the product being ordered",
            example ="1"
    )
    private Long productId;
    @Schema(
            description = "Name of the product being ordered",
            example = "Smartphone"
    )
    private String productName;
    @Schema(
            description = "Description of the product being ordered",
            example = "Latest model with advanced features"
    )
    private String description;
    @Schema(
            description = "Quantity of the product being ordered",
            example = "2"
    )
    private int quantity;
    @Schema(
            description = "Total price of the order",
            example = "599.98"
    )
    private Double totalPrice;
    @Schema(
            description = "Date and time when the order was placed",
            example = "2023-10-01T12:00:00"
    )
    private LocalDateTime orderDate;
    @Schema(
            description = "Address where the order will be delivered",
            example = "Mahadevapura"
    )
    private String address;
    @Schema(
            description = "City where the order will be delivered",
            example = "Bangalore"
    )
    private String city;
    @Schema(
            description = "State where the order will be delivered",
            example = "Karnataka"
    )
    private String state;
    @Schema(
            description = "Pin code for the delivery address",
            example = "560048"
    )
    private String pinCode;
}
