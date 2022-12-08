package com.spring.batch.service.controller;


import com.spring.batch.service.entity.Customer;
import com.spring.batch.service.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @PostMapping("/newCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.saveCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> loadAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomerInfo(),HttpStatus.OK);
    }
}
