package com.example.management.infrastructure.adapters.out;

import com.example.management.application.ports.out.OrderRepositoryPort;
import com.example.management.domain.model.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Adaptador de salida en memoria para la entidad {@link Order}.
 *
 * <p>
 * Guardian: esta clase reemplaza al antiguo {@code OrderJpaAdapter} para
 * reflejar correctamente que la persistencia se realiza en memoria usando
 * {@link ConcurrentHashMap} y {@link AtomicLong}.
 * </p>
 */
public class OrderInMemoryAdapter implements OrderRepositoryPort {

    private final Map<Long, Order> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0L);

    @Override
    public Order save(Order order) {
        Order orderToPersist = ensureId(order);
        store.put(orderToPersist.getId(), orderToPersist);
        return orderToPersist;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Order> findAll() {
        // Devolvemos una copia inmodificable para evitar que el llamador
        // pueda alterar el estado interno del adaptador.
        return List.copyOf(store.values());
    }

    /**
     * Garantiza que la orden tenga un identificador asignado.
     * <p>
     * Si la orden ya tiene id, se devuelve tal cual; en caso contrario,
     * se crea una nueva instancia con un identificador generado.
     * </p>
     */
    private Order ensureId(Order order) {
        if (order.getId() != null) {
            return order;
        }
        long nextId = sequence.incrementAndGet();
        return new Order(nextId, order.getDescription(), order.getCreatedAt());
    }
}

