package com.example.inventory_service.controller;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/check")
    public boolean checkStock(
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return inventoryService.isInStock(productId, quantity);
    }
}
