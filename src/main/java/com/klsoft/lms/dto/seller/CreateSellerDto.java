package com.klsoft.lms.dto.seller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateSellerDto {
    @NotEmpty(message = "name must be not missing or empty!")
    @Size(min = 2, max = 50, message = "username must not be less than 5 and greater than 50 characters!")
    private String name;

    @NotEmpty(message = "address must be not missing or empty!")
    @Size(min = 5, max = 250, message = "address must not be less than 5 and greater than 250 characters!")
    private String address;

    @NotEmpty(message = "phone1 must be not missing or empty!")
    @Size(min = 9, max = 12, message = "phone1 must not be less than 9 and greater than 12 characters!")
    private String phone1;

    private String phone2;

    private Date createdDate = new Date();

    @Pattern(regexp = "^true$|^false$", message = "status's value must be boolean type: true or false!")
    private String status = "true";
}
