package org.gustavosdaniel.ecommerce.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(

        Integer idProduct,

        String name,

        String description,

        BigDecimal price,

        Double quantity
) {
}
