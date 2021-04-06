package com.klsoft.lms.controller;

import com.klsoft.lms.dto.customer.CreateCustomerDto;
import com.klsoft.lms.dto.customer.UpdateCustomerDto;
import com.klsoft.lms.service.CustomerService;
import com.klsoft.lms.dto.ResponseDto;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerRestController {

    private CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @GetMapping
    public ResponseDto findAll() {
        return this.customerService.findAll();
    }

    @GetMapping("/{customerId}")
    public ResponseDto findOne(@PathVariable int customerId) {

        return this.customerService.findOne(customerId);
    }

    @PostMapping
    public ResponseDto create(@RequestBody CreateCustomerDto createCustomerDto) {
        return this.customerService.create(createCustomerDto);
    }

    @PostMapping("/update")
    public ResponseDto update(@RequestBody UpdateCustomerDto updateCustomerDto) {
        return this.customerService.update(updateCustomerDto);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseDto delete(@PathVariable int customerId) {
        return this.customerService.delete(customerId);
    }

}
