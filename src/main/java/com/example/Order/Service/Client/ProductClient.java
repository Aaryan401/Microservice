package com.example.Order.Service.Client;


import com.example.Order.Service.Model.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="Product-Service")    //We need to mention the application name in the @FeignClient
public interface ProductClient {
    @GetMapping("api/product-service/v1/get-specific-product/{pid}")
    public ProductDto getProductById(@PathVariable(name="pid") Long productId);

}
