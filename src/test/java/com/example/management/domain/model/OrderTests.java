package com.example.management.domain.model;

import com.example.management.domain.exception.InvalidOrderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Order")
class OrderTests {

    @Nested
    @DisplayName("Valid Creation")
    class ValidCreation {
        @Test
        void totalAmountIsSumOfLineTotals() {
            OrderLine line1 = OrderLine.of("p1", 2, new BigDecimal("10.00"));
            OrderLine line2 = OrderLine.of("p2", 1, new BigDecimal("5.50"));
            Order order = Order.create("order-1", "customer-1", List.of(line1, line2));

            assertEquals(new BigDecimal("25.50"), order.getTotalAmount());
        }

        @Test
        void exposesIdCustomerIdStatusAndLines() {
            OrderLine line = OrderLine.of("p1", 1, new BigDecimal("10.00"));
            Order order = Order.create("order-1", "customer-1", List.of(line));

            assertEquals("order-1", order.getId());
            assertEquals("customer-1", order.getCustomerId());
            assertEquals(OrderStatus.DRAFT, order.getStatus());
            assertEquals(1, order.getLines().size());
        }
    }

    @Nested
    @DisplayName("Invariants")
    class Invariants {
        @Test
        void rejectsEmptyListOfLines() {
            assertThrows(InvalidOrderException.class,
                    () -> Order.create("order-1", "customer-1", List.of()));
        }

        @Test
        void rejectsNullLines() {
            assertThrows(InvalidOrderException.class,
                    () -> Order.create("order-1", "customer-1", null));
        }

        @Test
        void recalculatedTotalMatchesSumOfLines() {
            OrderLine line1 = OrderLine.of("p1", 3, new BigDecimal("2.50"));
            OrderLine line2 = OrderLine.of("p2", 2, new BigDecimal("4.00"));
            Order order = Order.create("order-1", "customer-1", List.of(line1, line2));

            BigDecimal expected = new BigDecimal("7.50").add(new BigDecimal("8.00"));
            assertEquals(expected, order.getTotalAmount());
        }
    }

    @Nested
    @DisplayName("Confirm Order")
    class Confirm {
        @Test
        void changesStatusToConfirmed() {
            OrderLine line = OrderLine.of("p1", 1, new BigDecimal("10.00"));
            Order order = Order.create("order-1", "customer-1", List.of(line));

            order.confirm();

            assertEquals(OrderStatus.CONFIRMED, order.getStatus());
        }
    }
}

