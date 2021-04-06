package com.klsoft.lms.service.impl;

import com.klsoft.lms.dto.customer.CreateCustomerDto;
import com.klsoft.lms.dto.customer.UpdateCustomerDto;
import com.klsoft.lms.entity.Customer;
import com.klsoft.lms.exception.RequestException;
import com.klsoft.lms.repository.CustomerRepository;
import com.klsoft.lms.service.CustomerService;
import com.klsoft.lms.dto.ResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    private static final String NOT_FOUND = "Customer with id %s not found!";
    private static final String UPDATE_SUCCESS = "Customer Update Success!";
    private static final String CREATE_SUCCESS = "Customer Create Success!";
    private static final String DELETE_SUCCESS = "Customer Delete Success!";
    private static final String FIND_SUCCESS = "Seller find successfully";


    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper){
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDto findAll() throws RequestException{
        Iterable<Customer> customerList = this.customerRepository.findAllByStatus(true);
        return new ResponseDto("Customer find successfully", customerList);
    }

    @Override
    public ResponseDto findOne(int customerId) throws RequestException {
        Customer customer = this.checkExistingCustomer(customerId);
        return new ResponseDto(FIND_SUCCESS, customer);
    }

    @Override
    public ResponseDto create(CreateCustomerDto createCustomerDto) throws RequestException {
        Customer customer = modelMapper.map(createCustomerDto, Customer.class);
        customer = this.customerRepository.save(customer);
        return new ResponseDto(CREATE_SUCCESS, customer);
    }

    @Override
    public ResponseDto update(UpdateCustomerDto updateCustomerDto) throws RequestException{

        this.checkExistingCustomer(updateCustomerDto.getId());
        Customer customer = modelMapper.map(updateCustomerDto, Customer.class);
        customer = this.customerRepository.save(customer);
        return new ResponseDto(UPDATE_SUCCESS, customer);
    }

    @Override
    public ResponseDto delete(int id) throws RequestException {
        Customer customer = this.checkExistingCustomer(id);
        customer.setStatus(false);
        this.customerRepository.save(customer);
        return new ResponseDto(DELETE_SUCCESS);
    }

    private Customer checkExistingCustomer(int customerId) throws RequestException {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new RequestException(String.format(NOT_FOUND, customerId));
    }
}
