package com.example.management.domain.model;

import java.time.LocalDateTime;

public class Order {

    private Long id;
    private String description;
    private LocalDateTime createdAt;

    public Order(Long id, String description, LocalDateTime createdAt) {
        this.id = id;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

