package org.gustavosdaniel.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PurchaseRequest(

    @NotNull(message = "Produto é obrigatório")
    Long productId,

    @Positive(message = "Quantidade é obrigatória")
    Double quantity

) {
}
