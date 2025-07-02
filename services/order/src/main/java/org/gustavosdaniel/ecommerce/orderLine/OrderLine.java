package org.gustavosdaniel.ecommerce.orderLine;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.gustavosdaniel.ecommerce.Order;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderLine {

    @Id
    private Long id;
    private Integer productId;
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;
    private Double quantity;
}
