package org.gustavosdaniel.ecommer.custumer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    String id,

    @NotNull(message = "Nome do cliente é obrigatório")
    String firstName,

    @NotNull(message = "Sobrenome é obrigatório")
    String lastName,

    @NotNull(message = "Email é obrigatório")
    @Email(message = "Não é um endereço de email valido")
    String email,

    Address address
) {
}
