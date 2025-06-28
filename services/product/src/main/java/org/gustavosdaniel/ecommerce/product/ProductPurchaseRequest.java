package org.gustavosdaniel.ecommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(

        @NotNull(message = "Produto é obrigatório")
        Integer idProduct,

        @NotNull(message = "Por favor informe a quantiidade")
        Double quantity


) {
}
