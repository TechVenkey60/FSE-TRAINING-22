package com.spring.batch.service.service;


import com.spring.batch.service.config.advice.annotation.TrackExecutionTime;
import com.spring.batch.service.entity.Customer;
import com.spring.batch.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl {

    private final CustomerRepository customerRepository;

    @TrackExecutionTime
    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    @TrackExecutionTime
    public List<Customer> getAllCustomerInfo(){
        return customerRepository.findAll();
    }
}
