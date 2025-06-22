package com.example.Product.Service.Controller;


import com.example.Product.Service.Entity.Product;
import com.example.Product.Service.Model.ProductDto;
import com.example.Product.Service.Service.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product-service/v1")
@RequiredArgsConstructor
@Tag(
        name = "Product Service",
        description = "This is the Product Service API which provides endpoints to manage products."
)
public class ProductController {

    @Autowired
    private final ProductServiceImpl productService;

    @Operation(
            summary = "Register a new product",
            description = "This endpoint allows you to register a new product in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK: Product successfully registered"
    )
    @PostMapping("save-product")
    public ResponseEntity<String> registerProduct(@Parameter(description = "Give the product details to register a new product", required = true)
                                                  @RequestBody Product product){
        String response = productService.saveProduct(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);  //Using Object of ResponseEntity
    }

    @Operation(
            summary = "Get all products",
            description = "This endpoint retrieves a list of all products available in the system."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK: It give the retrieve the list of products"
    )
    @GetMapping("get-all-products")
    public ResponseEntity<List<Product>> getProductsList(){
        List<Product> product = productService.findProduct();
        return ResponseEntity.ok(product);  //Using builder to build the ResponseEntity
    }

    @Operation(
            summary = "Get a specific product by productID",
            description = "This endpoint retrieves a specific product based on the provided product ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK: It Successfully retrieved the product with the given ID"
    )
    @GetMapping("get-specific-product/{pid}")
    public ResponseEntity<Product> getSpecificProductById(@Parameter(description = "Give the Product ID to retrieve the specific product", required = true)
                                                          @PathVariable(name="pid") Long productId){
        Product foundProduct = productService.findProductById(productId);
        return ResponseEntity.ok(foundProduct);
    }

    @Operation(
            summary = "Add quantity to a specific product",
            description = "This endpoint allows you to add quantity to a specific product based on the provided product ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK: It Successfully added the quantity to the product"
    )
    @PutMapping("add-quantity/{productId}")
    public ResponseEntity<Product> addProductQuantity(@Parameter(description = "Give the Product ID in which the quantity u wanna to be add", required = true)
                                                      @PathVariable Long productId,
                                                      @Parameter(description = "Provide the product details including quantity to be added", required = true)
                                                      @Valid @RequestBody ProductDto productDto){
        Product addQuantity = productService.addQuantity(productId, productDto);
        return new ResponseEntity<>(addQuantity,HttpStatus.OK);
    }

    @Operation(
            summary = "Subtract quantity from a specific product",
            description = "This endpoint allows you to subtract quantity from a specific product based on the provided product ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK: It Successfully subtracted the quantity from the product"
    )
    @PutMapping("subtract-quantity/{productId}")
    public ResponseEntity<Product> subtractProductQuantity(@Parameter(description = "Give the Product ID in which the quantity u wanna to be subtracted", required = true)
                                                           @PathVariable Long productId,
                                                           @Parameter(description = "Provide the product details and when the any user place order the quantity will be subtracted", required = true)
                                                           @Valid @RequestBody ProductDto productDto){
        Product subtractQuantity = productService.subtractQuantity(productId, productDto);
        return new ResponseEntity<>(subtractQuantity,HttpStatus.OK);
    }

}
