package com.spring.batch.service.config;

import com.spring.batch.service.entity.Customer;
import org.springframework.batch.item.ItemProcessor;

public class CustomerItemProcessor implements ItemProcessor<Customer,Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        if("United States".equals(customer.getCountry())){
          return customer;
        }else {
            return null;
        }
    }
}
