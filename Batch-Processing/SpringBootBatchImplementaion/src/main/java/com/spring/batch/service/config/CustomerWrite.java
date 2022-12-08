package com.spring.batch.service.config;

import com.spring.batch.service.entity.Customer;
import com.spring.batch.service.repository.CustomerRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerWrite implements ItemWriter<Customer> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void write(List<? extends Customer> customers) throws Exception {
        System.out.println("Thread Name : => "+Thread.currentThread().getName());
        customerRepository.saveAll(customers);
    }
}
