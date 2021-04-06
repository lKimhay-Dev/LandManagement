package com.klsoft.lms.repository;

import com.klsoft.lms.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
//    Customer findCustomerById(String idCard);
    Iterable<Customer> findAllByStatus(boolean status);

}
