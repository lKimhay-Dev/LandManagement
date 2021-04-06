package com.klsoft.lms.dto;

import com.klsoft.lms.entity.Customer;
import com.klsoft.lms.entity.SellLandDetail;
import com.klsoft.lms.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SellDto {

    private Integer id;
    private Boolean isInstallment = true;
    private String description;

    @NotNull(message = "seller must be not missing or empty!")
    private Seller seller;

    @NotNull(message = "customers must be not missing or empty!")
    private Set<Customer> customers;

    @NotEmpty(message = "sellLandDetails list cannot be empty!")
    @NotNull(message = "sellLandDetails must be not missing or empty!")
    private Set<@Valid SellLandDetail> sellLandDetails;

}
