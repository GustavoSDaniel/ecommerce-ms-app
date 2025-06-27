package org.gustavosdaniel.ecommer.custumer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustumerController {

    private CustumerService custumerService;

    @PostMapping
    public ResponseEntity<String> createCustumer(@RequestBody @Valid CustumerRequest custumerRequest) {
        return ResponseEntity.ok(custumerService.createCustumer(custumerRequest));
    }
}
