package org.gustavosdaniel.ecommerce.product;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.gustavosdaniel.ecommerce.category.Category;
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
                .availableQuantity(productRequest.availableQuantity())
                .price(productRequest.price())
                //Classe categoria
                .category(
                        Category.builder()
                                .id(productRequest.idCategory())
                                .build()

                )
                .build();
    }

    public ProductResponse fromProduct(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                // Essas são da classe caregoria
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
                );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product,  Double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
