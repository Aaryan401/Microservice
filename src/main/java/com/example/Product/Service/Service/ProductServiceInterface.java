package com.example.Product.Service.Service;

import com.example.Product.Service.Entity.Product;
import com.example.Product.Service.Model.ProductDto;

import java.util.List;

public interface ProductServiceInterface {
    public String saveProduct(Product product);
    public List<Product> findProduct();

    public Product addQuantity(Long productId, ProductDto productDto);
    public Product subtractQuantity(Long productId,ProductDto productDto);
}
