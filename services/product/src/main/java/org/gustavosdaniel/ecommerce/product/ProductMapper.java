package org.gustavosdaniel.ecommerce.product;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(@Valid ProductRequest productRequest) {

        if (productRequest == null) {
            return null;
        }

        return Product.builder()
                .id(productRequest.id())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .category(productRequest.category())
                .build();
    }

    public ProductResponse fromProduct(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory());
    }
}
