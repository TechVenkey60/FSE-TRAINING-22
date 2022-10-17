package com.employee.test.service;


import com.employee.test.entity.Employee;
import com.employee.test.response.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Employee> getEmployeeById(Integer employeeId) {
        return iEmployeeRepository.findById(employeeId);
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) throws Exception {
        Optional<Employee> employee = getEmployeeById(employeeId);
        if(employee.isPresent()) {
            iEmployeeRepository.deleteById(employeeId);
            System.out.println("Employee data with Id :" + employeeId + " has been deleted.");
        }else {
            throw new Exception("EmployeeId  :" + employeeId + " not found");
        }

    }

    @Override
    public Employee updateEmployee(Employee employee, Integer employeeId) {

        var persistedEmployee =  iEmployeeRepository.findById(employeeId)
                .orElseThrow(() -> new DataNotFoundException("Employee","id",employeeId.toString()));

        persistedEmployee.setEmployeeName(employee.getEmployeeName());
        persistedEmployee.setEmployeePosition(employee.getEmployeePosition());
        persistedEmployee.setSalary(employee.getSalary());
        persistedEmployee.setQualification(employee.getQualification());

        return iEmployeeRepository.saveAndFlush(persistedEmployee);
    }


}
