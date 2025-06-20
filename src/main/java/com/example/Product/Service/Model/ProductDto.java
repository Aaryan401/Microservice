package com.example.Product.Service.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private Long productId;

    @NotBlank(message ="product Name must nt be blank")
    private String productName;

    @NotBlank
    private String description;

   @NotNull(message = "Quantity must not be null")
    private int quantity;

   @NotNull(message = "Price mst not be null")
    private Double price;
}
