package com.example.management.domain.model;

import java.time.LocalDateTime;

/**
 * Representa una orden dentro del dominio.
 *
 * <p>
 * La clase es inmutable: todos los atributos son {@code final} y sólo pueden
 * establecerse en el momento de la construcción. Esto facilita el
 * razonamiento, mejora la seguridad en entornos concurrentes y evita estados
 * intermedios inconsistentes sin cambiar el comportamiento observable.
 * </p>
 */
public final class Order {

    private final Long id;
    private final String description;
    private final LocalDateTime createdAt;

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

