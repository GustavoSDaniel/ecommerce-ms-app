package org.gustavosdaniel.ecommerce;

import org.gustavosdaniel.ecommerce.orderLine.OrderLine;

import java.time.LocalDate;

public record OrderResponse(

        Long id,

        LocalDate orderDate,

        String reference,

        OrderLine orderLine
) {
}
