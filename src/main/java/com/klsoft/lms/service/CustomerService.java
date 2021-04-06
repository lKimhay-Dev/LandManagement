package com.klsoft.lms.service;

import com.klsoft.lms.dto.customer.CreateCustomerDto;
import com.klsoft.lms.dto.ResponseDto;
import com.klsoft.lms.dto.customer.UpdateCustomerDto;

public interface CustomerService {
    ResponseDto findAll();

    ResponseDto findOne(int customerId);

    ResponseDto create(CreateCustomerDto createCustomerDto);

    ResponseDto update(UpdateCustomerDto createCustomerDto);

    ResponseDto delete(int id);
}
