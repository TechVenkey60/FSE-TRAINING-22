package com.employee.ms.demo.service;

import com.employee.ms.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class EmployeeServiceImpl implements IEmployeeService{

    List<Employee> employees = List.of(
               new Employee(100,"Venkat","987766561"),
               new Employee(102,"Suman","9877665226"),
               new Employee(103,"Raj","9877623656"),
               new Employee(104,"Chinna","9877896656"),
               new Employee(105,"Mohan","9879876656"),
               new Employee(106,"Gani","98789976656"),
               new Employee(107,"Bala","9870076656")
             );

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findAny()
                .orElse(null);
    }
}
