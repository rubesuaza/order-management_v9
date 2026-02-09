package com.example.management.infrastructure.adapters.out;

import com.example.management.application.ports.out.OrderRepositoryPort;
import com.example.management.domain.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class OrderJpaAdapter implements OrderRepositoryPort {

    private final Map<Long, Order> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public Order save(Order order) {
        Long id = order.getId();
        if (id == null) {
            id = sequence.incrementAndGet();
            // Creamos una nueva instancia con el identificador asignado
            order = new Order(id, order.getDescription(), order.getCreatedAt());
        }
        store.put(id, order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(store.values());
    }
}

