package com.example.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory_service.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}
