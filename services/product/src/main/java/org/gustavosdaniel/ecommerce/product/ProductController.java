package org.gustavosdaniel.ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(@RequestBody @Valid  ProductRequest productRequest) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> productPurchase(
            @RequestBody @Valid List<ProductPurchaseRequest> productPurchaseRequests ){

        return ResponseEntity.ok(productService.purchaseProduccts(productPurchaseRequests));
    }

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid  ProductRequest productRequest) {
        productService.updateProduct(productRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {

        return ResponseEntity.ok(productService.findAllProducts());
    }


    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findByIdProduct(@PathVariable("product-id") Integer productId) {

        return ResponseEntity.ok(productService.findByIdProduct(productId));
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductResponse>> findByNameStartingWith(@PathVariable("name") String productName) {
       ;

        return ResponseEntity.ok( productService.findByNameStartingWith(productName));
    }


    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteByIdProduct(@PathVariable("product-id") @Valid Integer productId) {
        productService.deleteByIdProduct(productId);
       return ResponseEntity.ok().build();
    }
}
