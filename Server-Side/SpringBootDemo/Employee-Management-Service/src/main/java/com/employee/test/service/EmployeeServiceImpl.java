package com.employee.test.service;


import com.employee.test.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        var savedEmployee = iEmployeeRepository.save(employee);
        return savedEmployee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return iEmployeeRepository.findAll();
    }
}
