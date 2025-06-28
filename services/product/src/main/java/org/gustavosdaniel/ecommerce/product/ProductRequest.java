package org.gustavosdaniel.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import org.gustavosdaniel.ecommerce.category.Category;

import java.math.BigDecimal;

public record ProductRequest(

        Integer id,

        @NotNull(message = "Nome do produto é obrigatório")
        String name,

        @NotNull(message = "O campo de descrição é obrigatório")
        String description,

        @NotNull(message = "O campo preço é obrigatório")
        BigDecimal price,

        @NotNull(message = "O campo de categoria é obrigatório")
        Category category

        ) {
}
