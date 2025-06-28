package org.gustavosdaniel.ecommerce.product;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.gustavosdaniel.ecommerce.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Integer createProduct( ProductRequest productRequest) {

        Product product = productRepository.save(productMapper.toProduct(productRequest));

        return product.getId();
    }

    public void updateProduct( ProductRequest productRequest) {

        Product updatedProduct = productRepository.findById(productRequest.id())
                .orElseThrow(() -> new ProductNotFoundException(format("Id informado não é valido %s", productRequest.id())));
       mergeProduct(updatedProduct, productRequest);
       productRepository.save(updatedProduct);
    }

    private void mergeProduct(Product updatedProduct, ProductRequest productRequest) {

        if (StringUtils.isNotBlank(productRequest.name())) {
            updatedProduct.setName(productRequest.name());
        }
        if (StringUtils.isNotBlank(productRequest.description())) {
            updatedProduct.setName(productRequest.description());
        }
        if (productRequest.price() != null) {
            updatedProduct.setDescription(productRequest.price().toString());
        }
        if (productRequest.category() != null) {
            updatedProduct.setCategory(productRequest.category());
        }
    }

    public List<ProductResponse> findAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::fromProduct)
                .collect(Collectors.toList());
    }

    public Boolean existsProduct(Integer productId) {

        return productRepository.findById(productId).isPresent();
    }

    public Integer findByIdProduct(Integer productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(format("O Id solicitado não existe %s", productId)));
        return productId;
    }


    public Void deleteByIdProduct(Integer productId) {

        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(format("\"O Id solicitado não existe %s\", productId")));

        productRepository.deleteById(productId);

        return null;

    }
}
