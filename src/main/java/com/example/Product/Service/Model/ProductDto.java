package com.example.Product.Service.Model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    @Schema( description = "Unique identifier for the product",
            example = "1")
    private Long productId;

    @Schema(description = "Name of the product",
            example = "Laptop")

    @NotBlank(message ="product Name must nt be blank")
    private String productName;

    @Schema(description = "Description of the product",
            example = "High performance laptop with 16GB RAM and 512GB SSD")
    @NotBlank
    private String description;

    @Schema(description = "Available quantity of the product",
            example = "50")
   @NotNull(message = "Quantity must not be null")
    private int quantity;

    @Schema(description = "Price of the product",
            example = "999.99")
   @NotNull(message = "Price mst not be null")
    private Double price;
}
