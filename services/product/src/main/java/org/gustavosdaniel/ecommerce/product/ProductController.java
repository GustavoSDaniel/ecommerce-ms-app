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

    @PutMapping
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid  ProductRequest productRequest) {
        productService.updateProduct(productRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {

        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("exists/{product-id}")
    public ResponseEntity<Boolean> existsProduct(@PathVariable("product-id") Integer productId) {

        return ResponseEntity.ok(productService.existsProduct(productId));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findByIdProduct(@PathVariable("product-id") Integer productId) {
        productService.findByIdProduct(productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<Void> deleteByIdProduct(@PathVariable("product-id") Integer productId) {
        productService.deleteByIdProduct(productId);
       return ResponseEntity.accepted().build();
    }
}
