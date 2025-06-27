package org.gustavosdaniel.ecommer.custumer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Validated
public class Address {

    private String street;
    private String houserNumber;
    private String zipCode;
}
