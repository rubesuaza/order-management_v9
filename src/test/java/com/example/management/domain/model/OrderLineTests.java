package com.example.management.domain.model;

import com.example.management.domain.exception.InvalidOrderLineException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("OrderLine")
class OrderLineTests {

    @Nested
    @DisplayName("valid creation")
    class ValidCreation {
        @Test
        void calculates_lineTotal_as_quantity_times_unitPrice() {
            OrderLine line = OrderLine.of("prod-1", 2, new BigDecimal("10.50"));
            assertEquals(new BigDecimal("21.00"), line.getLineTotal());
        }

        @Test
        void exposes_productId_quantity_and_unitPrice() {
            OrderLine line = OrderLine.of("prod-2", 3, new BigDecimal("5.00"));
            assertEquals("prod-2", line.getProductId());
            assertEquals(3, line.getQuantity());
            assertEquals(new BigDecimal("5.00"), line.getUnitPrice());
        }
    }

    @Nested
    @DisplayName("invariants")
    class Invariants {
        @Test
        void rejects_zero_quantity() {
            assertThrows(InvalidOrderLineException.class,
                    () -> OrderLine.of("prod-1", 0, new BigDecimal("10.00")));
        }

        @Test
        void rejects_negative_quantity() {
            assertThrows(InvalidOrderLineException.class,
                    () -> OrderLine.of("prod-1", -1, new BigDecimal("10.00")));
        }

        @Test
        void rejects_negative_unitPrice() {
            assertThrows(InvalidOrderLineException.class,
                    () -> OrderLine.of("prod-1", 1, new BigDecimal("-0.01")));
        }

        @Test
        void accepts_zero_unitPrice() {
            OrderLine line = OrderLine.of("prod-1", 1, BigDecimal.ZERO);
            assertEquals(BigDecimal.ZERO, line.getLineTotal());
        }
    }
}

