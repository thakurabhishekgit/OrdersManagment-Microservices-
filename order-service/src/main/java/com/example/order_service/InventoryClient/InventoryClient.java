package com.example.order_service.InventoryClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public class InventoryClient {

    @GetMapping("api/v1/inventory/checkStock")
    boolean checkStock(
            @RequestParam Long productId,
            @RequestParam int quantity) {
        return false;
    }

}
