package com.example.Product.Service.Controller;


import com.example.Product.Service.Entity.Product;
import com.example.Product.Service.Model.ProductDto;
import com.example.Product.Service.Service.ProductServiceImpl;
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
public class ProductController {

    @Autowired
    private final ProductServiceImpl productService;

    @PostMapping("save-product")
    public ResponseEntity<String> registerProduct(@RequestBody Product product){
        String response = productService.saveProduct(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);  //Using Object of ResponseEntity
    }

    @GetMapping("get-all-products")
    public ResponseEntity<List<Product>> getProductsList(){
        List<Product> product = productService.findProduct();
        return ResponseEntity.ok(product);  //Using builder to build the ResponseEntity
    }

    @GetMapping("get-specific-product/{pid}")
    public ResponseEntity<Product> getSpecificProductById(@PathVariable(name="pid") Long productId){
        Product foundProduct = productService.findProductById(productId);
        return ResponseEntity.ok(foundProduct);
    }


    @PutMapping("add-quantity/{productId}")
    public ResponseEntity<Product> addProductQuantity(@PathVariable Long productId,@Valid @RequestBody ProductDto productDto){
        Product addQuantity = productService.addQuantity(productId, productDto);
        return new ResponseEntity<>(addQuantity,HttpStatus.OK);
    }

    @PutMapping("subtract-quantity/{productId}")
    public ResponseEntity<Product> subtractProductQuantity(@PathVariable Long productId,@Valid @RequestBody ProductDto productDto){
        Product subtractQuantity = productService.subtractQuantity(productId, productDto);
        return new ResponseEntity<>(subtractQuantity,HttpStatus.OK);
    }

}
