package com.example.Product.Service.Service;


import com.example.Product.Service.Entity.Product;
import com.example.Product.Service.Model.ProductDto;
import com.example.Product.Service.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductServiceInterface{

    @Autowired
    private final ProductRepository productRepository;


    @Override
    public String saveProduct(Product product) {
        productRepository.save(product);
        return "Product have been saved";
    }

    @Override
    public List<Product> findProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product addQuantity(Long productId, ProductDto productDto) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        foundProduct.setProductName(productDto.getProductName());
        foundProduct.setQuantity(productDto.getQuantity() + foundProduct.getQuantity());
        foundProduct.setPrice(productDto.getPrice());
        return productRepository.save(foundProduct);
    }

    @Override
    public Product subtractQuantity(Long productId, ProductDto productDto) {
        Product foundProduct = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        foundProduct.setProductName(productDto.getProductName());
        foundProduct.setQuantity(foundProduct.getQuantity() - productDto.getQuantity());
        foundProduct.setPrice(productDto.getPrice());
        return productRepository.save(foundProduct);
    }
}
