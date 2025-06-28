package org.gustavosdaniel.ecommer.custumer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

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
    } //
}
