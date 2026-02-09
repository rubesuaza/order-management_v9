package com.example.management.application.services;

import com.example.management.application.ports.in.OrderUseCase;
import com.example.management.application.ports.out.OrderRepositoryPort;
import com.example.management.domain.model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Implementación por defecto de los casos de uso de órdenes.
 */
@Service
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

