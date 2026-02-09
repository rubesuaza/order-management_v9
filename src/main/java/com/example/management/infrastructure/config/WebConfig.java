package com.example.management.infrastructure.config;

import com.example.management.application.ports.in.OrderUseCase;
import com.example.management.application.ports.out.OrderRepositoryPort;
import com.example.management.application.services.OrderService;
import com.example.management.infrastructure.adapters.out.OrderInMemoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    /**
     * Guardian: la capa de aplicación depende del puerto {@link OrderRepositoryPort},
     * y la implementación concreta pertenece a la infraestructura.
     */
    @Bean
    public OrderRepositoryPort orderRepositoryPort() {
        return new OrderInMemoryAdapter();
    }

    /**
     * Guardian: {@link OrderService} se expone como bean de Spring sin acoplar
     * la capa de aplicación al framework mediante anotaciones.
     */
    @Bean
    public OrderUseCase orderUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new OrderService(orderRepositoryPort);
    }
}


