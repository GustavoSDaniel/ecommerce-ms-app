package org.gustavosdaniel.ecommerce.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.gustavosdaniel.ecommerce.product.Product;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE) // QUANDO EU REMOVER A CATEGORIA REMOVE TODOS OS PRODUTOS DESSA CATEGORIA TBM
    private List<Product> products;
}
