package com.example.inventory_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory_service.entity.Product;
import com.example.inventory_service.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/check")
    public boolean checkStock(
            @RequestParam String productId,
            @RequestParam int quantity) {
        return inventoryService.isInStock(productId, quantity);
    }

    @PostMapping("/addStock")
    public Product addProduct(@RequestBody Product product) {
        return inventoryService.addProduct(product);
    }
}
