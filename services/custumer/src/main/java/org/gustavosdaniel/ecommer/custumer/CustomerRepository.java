package org.gustavosdaniel.ecommer.custumer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findByFirstNameStartingWithIgnoreCaseOrderByFirstNameAsc(String firstName);

}
