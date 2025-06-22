package com.example.Order.Service.Model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Schema(
            description = "Unique identifier for the product",
            example = "1"
    )
    private Long productId;
    @Schema(
            description = "Name of the product",
            example = "Smartphone"
    )
    private String productName;
    @Schema(
            description = "Description of the product",
            example = "Latest model with advanced features"
    )
    private String description;
    @Schema(
            description = "Available quantity of the product",
            example = "100"
    )
    private int quantity;
    @Schema(
            description = "Price of the product",
            example = "299.99"
    )
    private Double price;
}
