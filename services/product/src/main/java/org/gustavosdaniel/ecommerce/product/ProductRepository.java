package org.gustavosdaniel.ecommerce.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByIdInOrderById(List<Integer> productIds);

    List<Product> findByNameStartingWithIgnoreCaseOrderByNameAsc(String name);
}
