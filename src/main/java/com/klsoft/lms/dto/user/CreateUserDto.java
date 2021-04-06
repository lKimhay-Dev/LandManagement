package com.klsoft.lms.dto.user;

import com.klsoft.lms.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserDto {
    private Seller seller;

    @NotEmpty(message = "username cannot be missing or empty!")
    @Size(min = 2, max = 50, message = "username must not be less than 2 and greater than 50 characters!")
    private String username;

    @NotEmpty(message = "password cannot be missing or empty!")
    @Size(min = 4, max = 50, message = "username must not be less than 4 and greater than 50 characters!")
    private String password;

    @NotEmpty(message = "role cannot be missing or empty!")
    @Size(min = 4, max = 50, message = "role must not be less than 4 and greater than 50 characters!")
    private String role;

    @Pattern(regexp = "^true$|^false$", message = "status's value must be boolean type: true or false!")
    private String status = "true";
}
