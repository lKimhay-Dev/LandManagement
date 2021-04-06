package com.klsoft.lms.dto.customer;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UpdateCustomerDto extends CreateCustomerDto{
    @Positive(message = "id must greater than 0!")
    @NotNull(message = "id must be not missing!")
    private Integer id;
}
