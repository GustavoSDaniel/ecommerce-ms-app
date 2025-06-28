package org.gustavosdaniel.ecommerce.product;


import java.math.BigDecimal;

public record ProductResponse(

        Integer id,

        String name,

        String description,

        Double availableQuantity,

        BigDecimal price,

        Integer idCategory,

        String categoryName,

        String categoryDescription

) {
}
