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
}
