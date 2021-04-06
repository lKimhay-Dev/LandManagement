package com.klsoft.lms.dto.customer;
import com.klsoft.lms.entity.CustomerLandTitle;
import com.klsoft.lms.entity.Deposit;
import com.klsoft.lms.entity.Installment;
import com.klsoft.lms.entity.Sell;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateCustomerDto {
    private String name;
    private Date dob = new Date();
    private String address;
    private String idCard;
    private String idCardNo;
    private Date idCardIssueDate = new Date();
    private String gender;
    private String nationality;
    private String relationship;
    private String phone1;
    private String phone2;
    private Sell sells;
    private Deposit deposits;
    private Installment installments;
    private CustomerLandTitle customerLandTitles;

    @Pattern(regexp = "^true$|^false$", message = "status's value must be boolean type: true or false!")
    private String status = "true";
}
