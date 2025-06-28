package org.gustavosdaniel.ecommerce.product;

import jakarta.persistence.*;
import lombok.*;
import org.gustavosdaniel.ecommerce.category.Category;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private Double availableQuantity;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;


}
