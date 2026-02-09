package com.example.management.application.services;

import com.example.management.application.ports.in.OrderUseCase;
import com.example.management.application.ports.out.OrderRepositoryPort;
import com.example.management.domain.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementación por defecto de los casos de uso de órdenes.
 *
 * <p>
 * Guardian: esta clase forma parte de la capa de aplicación y permanece
 * libre de anotaciones de framework (por ejemplo, {@code @Service}). La
 * integración con Spring se realiza desde la capa de infraestructura
 * mediante configuración explícita.
 * </p>
 */
public class OrderService implements OrderUseCase {

    private final OrderRepositoryPort orderRepositoryPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order createOrder(String description) {
        Order order = new Order(null, description, LocalDateTime.now());
        return orderRepositoryPort.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepositoryPort.findById(id);
    }

    @Override
    public List<Order> listOrders() {
        return orderRepositoryPort.findAll();
    }
}

