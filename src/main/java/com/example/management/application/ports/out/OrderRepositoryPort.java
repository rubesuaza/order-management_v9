package com.example.management.application.ports.out;

import com.example.management.domain.model.Order;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida para la persistencia de órdenes.
 *
 * Implementaciones típicas pueden usar bases de datos relacionales,
 * almacenes en memoria u otros sistemas externos.
 */
public interface OrderRepositoryPort {

    /**
     * Persiste una orden.
     *
     * @param order orden a persistir
     * @return la orden persistida, potencialmente con su identificador actualizado
     */
    Order save(Order order);

    /**
     * Busca una orden por su identificador.
     *
     * @param id identificador de la orden
     * @return una {@link Optional} que contiene la orden si existe
     */
    Optional<Order> findById(Long id);

    /**
     * Recupera todas las órdenes existentes.
     *
     * @return lista de órdenes
     */
    List<Order> findAll();
}

