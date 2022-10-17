package com.employee.ms.demo.controller;


import com.employee.ms.demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.isNull;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;


    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<?> fetchEmployeeById(@PathVariable Integer employeeId){
        var employee = employeeService.getEmployeeById(employeeId);
        if(isNull(employee)){
            return new ResponseEntity<>(String.format("No Employee Found For The Given Id: %d ",employeeId),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
