package com.example.Order.Service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceOrderDto {

        @Schema(
                description = "Unique identifier for the product",
                example = "1"
        )
        private Long productId;
        @Schema(
                description = "Quantity of the product to be ordered",
                example = "2"
        )
        private int quantity;
        @Schema(
                description = "Give the Address",
                example = "Mahadevapura"
        )
        private String address;
        @Schema(
                description = "City where the order is placed",
                example = "Bangalore"
        )
        private String city;
        @Schema(
                description = "State where the order is placed",
                example = "Karnataka"
        )
        private String state;
        @Schema(
                description = "Pin code for the delivery address",
                example = "560048"
        )
        private String pinCode;
}
