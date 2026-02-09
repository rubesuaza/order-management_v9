package com.example.management.application.ports.in;

import com.example.management.domain.model.Order;

import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada para los casos de uso relacionados con órdenes.
 *
 * Define las operaciones que la capa de aplicación expone hacia los adaptadores
 * de entrada (por ejemplo, controladores HTTP, CLI, listeners de eventos).
 */
public interface OrderUseCase {

    /**
     * Crea una nueva orden a partir de una descripción.
     *
     * La estrategia de generación de identificadores y persistencia queda
     * delegada a la capa de salida a través de {@link com.example.management.application.ports.out.OrderRepositoryPort}.
     *
     * @param description descripción de la orden
     * @return la orden creada, incluyendo su identificador y fecha de creación
     */
    Order createOrder(String description);

    /**
     * Obtiene una orden existente por su identificador.
     *
     * @param id identificador de la orden
     * @return una {@link Optional} que contiene la orden si existe
     */
    Optional<Order> getOrderById(Long id);

    /**
     * Lista todas las órdenes registradas.
     *
     * @return lista de órdenes
     */
    List<Order> listOrders();
}

