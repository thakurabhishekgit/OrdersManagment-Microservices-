package com.example.inventory_service.service;

import org.springframework.stereotype.Service;

import com.example.inventory_service.entity.Product;
import com.example.inventory_service.repository.ProductRepository;

@Service
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean isInStock(String productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return product.getStock() >= quantity;
    }

    public Product addProduct(String name, int stock) {
        Product product = new Product();
        product.setName(name);
        product.setStock(stock);
        return productRepository.save(product);
    }

    public Product updateStockProductCount(String productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStock(product.getStock() - quantity);
        return productRepository.save(product);
    }
}
