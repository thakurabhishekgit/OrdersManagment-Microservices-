package com.example.order_service.service;

import org.springframework.stereotype.Service;

import com.example.order_service.InventoryClient.InventoryClient;
import com.example.order_service.entity.Order;
import com.example.order_service.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    // FUTURE (Kafka)
    // private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderService(
            OrderRepository orderRepository,
            InventoryClient inventoryClient
    // KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate
    ) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;

    }

    public Order placeOrder(Long userId, String productId, int quantity) {

        boolean inStock = inventoryClient.checkStock(productId, quantity);

        if (!inStock) {
            throw new RuntimeException("Product is out of stock");
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setStatus("CREATED");

        order = orderRepository.save(order);

        inventoryClient.updateStock(productId, quantity);

        // FUTURE: Kafka event
        /*
         * OrderCreatedEvent event =
         * new OrderCreatedEvent(order.getId(), userId, productId);
         * 
         * kafkaTemplate.send("order-created", event);
         */

        return order;
    }
}
