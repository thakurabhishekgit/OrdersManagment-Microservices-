package com.example.order_service.InventoryClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @GetMapping("/api/v1/inventory/check")
    boolean checkStock(
            @RequestParam String productId,
            @RequestParam int quantity);

    @PostMapping("/api/v1/inventory/updateStock")
    void updateStock(@RequestParam String productId, @RequestParam int quantity);
}
