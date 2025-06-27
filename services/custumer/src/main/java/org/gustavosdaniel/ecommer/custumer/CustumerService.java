package org.gustavosdaniel.ecommer.custumer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustumerService {


    public String createCustumer(@Valid CustumerRequest custumerRequest) {
        return null;
    }
}
