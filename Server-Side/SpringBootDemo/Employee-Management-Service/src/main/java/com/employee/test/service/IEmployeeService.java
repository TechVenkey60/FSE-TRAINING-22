package com.employee.test.service;

import com.employee.test.entity.Employee;
import java.util.List;

public interface IEmployeeService {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();
}
