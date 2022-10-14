package com.employee.test.service;

import com.employee.test.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Optional<Employee> getEmployeeById(Integer employeeId);
}
