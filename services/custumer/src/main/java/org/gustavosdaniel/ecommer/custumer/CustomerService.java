package org.gustavosdaniel.ecommer.custumer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.gustavosdaniel.ecommer.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public  Boolean existsByIdCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public String createCustumer(@Valid CustomerRequest customerRequest) {

        Customer customer = customerRepository.save(customerMapper.toCustumer(customerRequest));

        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest customerRequest) {

        Customer updateCustomer = customerRepository.findById(customerRequest.id())
                .orElseThrow(() -> new CustomerNotFoundException(format("Não foi possivel atualizar o Cliente:: Não foi encontrado nenhum Cliente com o Id informado:: %s", customerRequest.id())));
        mergeCustomer(updateCustomer, customerRequest); //operações de atualização para copiar seletivamente dados de um DTO (CustomerRequest)
        customerRepository.save(updateCustomer);
    }

    private void mergeCustomer(Customer updateCustomer, CustomerRequest customerRequest) {
        if (StringUtils.isNotBlank(customerRequest.firstName())) { // SE O NOME NÃO ESTIVER EM BRANCO
            updateCustomer.setFirstName(customerRequest.firstName()); // ALTERE O NOME
        }
        if (StringUtils.isNotBlank(customerRequest.lastName())) {
            updateCustomer.setFirstName(customerRequest.lastName());
        }
        if (StringUtils.isNotBlank(customerRequest.email())) {
            updateCustomer.setFirstName(customerRequest.email());
        }
        if (customerRequest.address() != null) { // SE A REQUISIÇÃO DO ENDEREÇO NÃO FOR NULA
            updateCustomer.setAddress(customerRequest.address()); // ALTERE  O ENDEREÇO
        }
    }

    public List<CustomerResponse> findAllCustomers() {

        return customerRepository.findAll()
                .stream() // Converte a lista em um Stream (fluxo de dados)
                .map(customerMapper::fromCustomer) //Transforma cada elemento
                .collect(Collectors.toList());
    }

    public CustomerResponse findByIdCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer) //Transforma cada elemento
                .orElseThrow(() -> new CustomerNotFoundException(format("Não existe cliente com esse ID:: %s", customerId)));
    }

    public List<CustomerResponse> findByFirstName(String firstName) {
        var customers = customerRepository.findByFirstNameStartingWithIgnoreCaseOrderByFirstNameAsc(firstName);

        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("Primeiro nnome do cliente não encontrado");
        }

        return customers.stream().map(customerMapper::fromCustomer).collect(Collectors.toList());
    }

    public void deleteByIdCustomer(String customerId) {

        customerRepository.deleteById(customerId);
    }
}
