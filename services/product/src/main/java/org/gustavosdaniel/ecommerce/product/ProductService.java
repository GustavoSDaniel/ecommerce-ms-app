package org.gustavosdaniel.ecommerce.product;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.gustavosdaniel.ecommerce.exception.ProductNotFoundException;
import org.gustavosdaniel.ecommerce.exception.ProductPurchaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    public Integer createProduct( ProductRequest productRequest) {

        Product product = productMapper.toProduct(productRequest);

        return productRepository.save(product).getId();
    }

    public ProductResponse findByIdProduct(Integer productId) {

        return productRepository.findById(productId)
                .map(productMapper::fromProduct)
                .orElseThrow(() -> new EntityNotFoundException((format("O Id solicitado não existe %s", productId))));
    }

    public List<ProductResponse> findAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::fromProduct)
                .collect(Collectors.toList());
    }



    public List<ProductPurchaseResponse> purchaseProduccts(List<ProductPurchaseRequest> productPurchaseRequests) {
        var productIds = productPurchaseRequests
                .stream()
                .map(ProductPurchaseRequest::idProduct)
                .toList();

        // Busca os produtos correspondentes no banco de dados
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);

        //Valida se todos os produtos existem
        if (productIds.size() != storedProducts.size() ) {
            throw new ProductPurchaseException("Um ou mais desses produtos não existem");
        }

        var storedRequest = productPurchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::idProduct)) // Ordena os Ids do menor para o maior
                .toList(); // Entrega a lista ordenada

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {

            var product = storedProducts.get(i); //Quantidade de itens do estoque
            var productRequest = storedRequest.get(i); // Quantidade de itens solicitados

            if (product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Quantidade insuficiente para o produto com o ID:: " + productRequest.idProduct());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);

            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }

            return purchasedProducts;
    }



    public void updateProduct( ProductRequest productRequest) {

        Product updatedProduct = productRepository.findById(productRequest.id())
                .orElseThrow(() -> new EntityNotFoundException(format("Id informado não é valido %s", productRequest.id())));
       mergeProduct(updatedProduct, productRequest);
       productRepository.save(updatedProduct);
    }

    private void mergeProduct(Product updatedProduct, ProductRequest productRequest) {

        if (productRequest.price() != null) {
            updatedProduct.setPrice(productRequest.price());
        }

    }


    public Void deleteByIdProduct(Integer productId) {

        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(format("\"O Id solicitado não existe %s\", productId")));

        productRepository.deleteById(productId);

        return null;

    }


    public List<ProductResponse> findByNameStartingWith(String productName) {

       List<Product> products = productRepository.findByNameStartingWithIgnoreCaseOrderByNameAsc(productName);

        if (products.isEmpty()) {
            throw new EntityNotFoundException(format("Produto não encontrado", productName));
        }

        return products
                .stream()
                .map(productMapper::fromProduct)
                .collect(Collectors.toList());

    }
}
