package org.gustavosdaniel.ecommer.custumer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ResponseEntity.ok(customerService.createCustumer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {

        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @GetMapping("exists/{customer-id}")
    public ResponseEntity<Boolean> existsByIdCustomer(@PathVariable("customer-id")String customerId) {
        return ResponseEntity.ok(customerService.existsByIdCustomer(customerId));
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findByIdCustomer(@PathVariable("customer-id")String customerId) {
        return ResponseEntity.ok(customerService.findByIdCustomer(customerId));
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteByIdCustomer(@PathVariable("customer-id") String customerId) {

        customerService.deleteByIdCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}


