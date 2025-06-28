package org.gustavosdaniel.ecommerce.product;

import org.gustavosdaniel.ecommerce.category.Category;

import java.math.BigDecimal;

public record ProductResponse(

        Integer id,

        String name,

        String description,

        BigDecimal price,

        Category category

) {
}
