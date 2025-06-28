package org.gustavosdaniel.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true) // faz com que os equals() e hashCode() gerados pelo Lombok também considerem os campos da classe pai (RuntimeException)
@Data
public class ProductNotFoundException extends RuntimeException {
    private final String message;
}
