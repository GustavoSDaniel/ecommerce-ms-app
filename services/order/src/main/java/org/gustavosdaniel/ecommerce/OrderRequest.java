package org.gustavosdaniel.ecommerce;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.gustavosdaniel.ecommerce.product.PurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Long id,

        String reference,

        @Positive(message = "O valor do pedido tem que ser positivo")
        BigDecimal totalAmount,

        @NotNull(message = "Precisa preencher o meio de pagamento")
        PaymentMethod paymentMethod,

        @NotNull(message = "O Cliente precisa ser informado")
        @NotEmpty(message = "O Cliente precisa ser informado")
        @NotBlank(message = "O Cliente precisa ser informado")
        String customerId,

        @NotEmpty(message = "Selecione pelo menos 1 produto")
        List<OrderRequest> products



) {
}
